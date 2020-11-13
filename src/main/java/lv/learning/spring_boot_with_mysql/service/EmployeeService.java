package lv.learning.spring_boot_with_mysql.service;

import lv.learning.spring_boot_with_mysql.model.entity.EmployeeEntity;
import lv.learning.spring_boot_with_mysql.model.request.EmployeeRequest;
import lv.learning.spring_boot_with_mysql.repository.EmployeeRepository;
import lv.learning.spring_boot_with_mysql.specification.EmployeeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // read all EmployeeEntity records
//    public List<EmployeeEntity> readAllEmployees() {
////        List<EmployeeEntity> employees = new ArrayList<>();
////        employeeRepository.findAll().forEach(employees::add);
////        return employees;
//        // shorter version of the same as above
//        return (List<EmployeeEntity>) employeeRepository.findAll();
//    }

    //    public Iterable<EmployeeEntity> readEmployees(Integer start, Integer limit, Character gender) {
    //public Object readEmployees(Integer start, Integer limit, Character gender, LocalDate hiredAfter, LocalDate hiredBefore, Sort sort) {
    public Page<EmployeeEntity> readEmployees(Character gender, LocalDate hiredAfter, LocalDate hiredBefore, Sort sort, Pageable pageRequest) {
//        if (gender == null && hiredAfter == null && hiredBefore == null) {
////            List<EmployeeEntity> employees = new ArrayList<>();
////            employeeRepository.findAll().forEach(employees::add);
////            return employees;
//            // shorter version of the same as above
//            return (List<EmployeeEntity>) employeeRepository.findAll();
//        }
//        if (gender != null) {
//            gender = Character.toUpperCase(gender);
//            if (Character.compare(gender, 'M') != 0 && Character.compare(gender, 'F') != 0) {
//                return new String("Allowed values for gender parameter are {F, M} wheres you provided \"" + gender + "\"");
//            } else {
////                return employeeRepository.findFirst10ByGender(gender);
//                return employeeRepository.findAll(Specification.where(gender == null ? null : EmployeeSpecification.hasGender(gender)));
//            }
//        }
//        if (hiredAfter != null) {
//            return employeeRepository.findFirst10ByHireDateAfter(hiredAfter);
////            return employeeRepository.findAll(Specification.where(hiredAfter == null ? null : EmployeeSpecification.hiredAfter(hiredAfter)));
//        }
//        if (hiredBefore != null) {
//            return employeeRepository.findFirst10ByHireDateBefore(hiredBefore);
////            return employeeRepository.findAll(Specification.where(hiredBefore == null ? null : EmployeeSpecification.hiredBefore(hiredBefore)));
//        }
//        return null;

//        return employeeRepository.findAll(Specification
//                        .where(gender == null ? null : EmployeeSpecification.hasGender(gender))
//                        .and(hiredAfter == null ? null : EmployeeSpecification.hiredAfter(hiredAfter))
//                        .and(hiredBefore == null ? null : EmployeeSpecification.hiredBefore(hiredBefore)),
//                //sort == null ? defaultOrderBy() : sort,
//                sort);
        Pageable paging = PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize(), sort);

        Page<EmployeeEntity> employees = employeeRepository.findAll(Specification
                        .where(gender == null ? null : EmployeeSpecification.hasGender(gender))
                        .and(hiredAfter == null ? null : EmployeeSpecification.hiredAfter(hiredAfter))
                        .and(hiredBefore == null ? null : EmployeeSpecification.hiredBefore(hiredBefore)),
                //sort == null ? defaultOrderBy() : sort,
                paging);

        return employees;
    }

    // does not work atm
//    public List<EmployeeEntity> findPaginated(Integer pageNo, Integer pageSize) {
//        Pageable paging = PageRequest.of(pageNo, pageSize);
//        Page<EmployeeEntity> pageResult = employeeRepository.findAll(paging);
//
//        return pageResult.toList();
//    }

    // read a single EmployeeEntity record
    public EmployeeEntity readEmployee(Integer emp_no) {
//        EmployeeEntity storedEmployee = employeeRepository.findById(emp_no).orElse(null);
//        return storedEmployee;
        return employeeRepository.findById(emp_no).orElse(null);
    }

    // create new EmployeeEntity record
    public EmployeeEntity createEmployee(EmployeeRequest employeeRequest) {
//        System.out.println("Gender char before = " + employeeRequest.getGender());
        employeeRequest.setGender(Character.toUpperCase(employeeRequest.getGender()));
//        System.out.println("Gender char after = " + employeeRequest.getGender());
        EmployeeEntity newEmployeeEntity = new EmployeeEntity();
        newEmployeeEntity.setBirthDate(employeeRequest.getBirthDate());
        newEmployeeEntity.setFirstName(employeeRequest.getFirstName());
        newEmployeeEntity.setLastName(employeeRequest.getLastName());
        newEmployeeEntity.setGender(employeeRequest.getGender());
        newEmployeeEntity.setHireDate(employeeRequest.getHireDate());
        return employeeRepository.save(newEmployeeEntity);
    }

    // update an EmployeeEntity record
    public EmployeeEntity updateEmployee(Integer emp_no, EmployeeRequest updateRequest) {
        EmployeeEntity storedEmployeeEntity = employeeRepository.findById(emp_no).orElse(null);
        if (storedEmployeeEntity != null) {
            if (updateRequest.getBirthDate() != null && !(updateRequest.getBirthDate().equals(storedEmployeeEntity.getBirthDate()))) {
                storedEmployeeEntity.setBirthDate(updateRequest.getBirthDate());
            }
            if (updateRequest.getFirstName() != null && !(updateRequest.getFirstName().equalsIgnoreCase(storedEmployeeEntity.getFirstName()))) {
                storedEmployeeEntity.setFirstName(updateRequest.getFirstName());
            }
            if (updateRequest.getLastName() != null && !(updateRequest.getLastName().equalsIgnoreCase(storedEmployeeEntity.getLastName()))) {
                storedEmployeeEntity.setLastName(updateRequest.getLastName());
            }
            if (updateRequest.getGender() != null) {
                updateRequest.setGender(Character.toUpperCase(updateRequest.getGender()));
                if (!(updateRequest.getGender().equals(storedEmployeeEntity.getGender()))) {
                    storedEmployeeEntity.setGender(updateRequest.getGender());
                }
            }
            if (updateRequest.getHireDate() != null && !(updateRequest.getHireDate().equals(storedEmployeeEntity.getHireDate()))) {
                storedEmployeeEntity.setHireDate(updateRequest.getHireDate());
            }

            return employeeRepository.save(storedEmployeeEntity);
        } else {
            return null;
        }
    }

    // delete an EmployeeEntity record
    public short deleteEmployee(Integer emp_no) {
        if (employeeRepository.findById(emp_no).orElse(null) != null) {
            employeeRepository.deleteById(emp_no);
            return 204;
        } else {
            return 404;
        }
    }

//    private Sort defaultOrderBy() {
//        System.out.println("marker!");
//        Sort sort = Sort.by(Sort.Direction.ASC, "lastName");
//        return sort;
//    }
}
