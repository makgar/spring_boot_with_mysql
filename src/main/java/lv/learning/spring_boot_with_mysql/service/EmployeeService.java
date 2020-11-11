package lv.learning.spring_boot_with_mysql.service;

import lv.learning.spring_boot_with_mysql.model.EmployeeRequest;
import lv.learning.spring_boot_with_mysql.model.EmployeeRest;
import lv.learning.spring_boot_with_mysql.repository.EmployeeRepository;
import lv.learning.spring_boot_with_mysql.specification.EmployeeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // read all Employee records
//    public List<EmployeeRest> readAllEmployees() {
////        List<EmployeeRest> employees = new ArrayList<>();
////        employeeRepository.findAll().forEach(employees::add);
////        return employees;
//        // shorter version of the same as above
//        return (List<EmployeeRest>) employeeRepository.findAll();
//    }

    //    public Iterable<EmployeeRest> readEmployees(Integer start, Integer limit, Character gender) {
    public Object readEmployees(Integer start, Integer limit, Character gender, LocalDate hiredAfter, LocalDate hiredBefore) {
//        if (gender == null && hiredAfter == null && hiredBefore == null) {
////            List<EmployeeRest> employees = new ArrayList<>();
////            employeeRepository.findAll().forEach(employees::add);
////            return employees;
//            // shorter version of the same as above
//            return (List<EmployeeRest>) employeeRepository.findAll();
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

        return employeeRepository.findAll(Specification
                .where(gender == null ? null : EmployeeSpecification.hasGender(gender))
                .and(hiredAfter == null ? null : EmployeeSpecification.hiredAfter(hiredAfter))
                .and(hiredBefore == null ? null : EmployeeSpecification.hiredBefore(hiredBefore)));
    }

    // does not work atm
//    public List<EmployeeRest> findPaginated(Integer pageNo, Integer pageSize) {
//        Pageable paging = PageRequest.of(pageNo, pageSize);
//        Page<EmployeeRest> pageResult = employeeRepository.findAll(paging);
//
//        return pageResult.toList();
//    }

    // read a single Employee record
    public EmployeeRest readEmployee(Integer emp_no) {
//        EmployeeRest storedEmployee = employeeRepository.findById(emp_no).orElse(null);
//        return storedEmployee;
        return employeeRepository.findById(emp_no).orElse(null);
    }

    // create new Employee record
    public EmployeeRest createEmployee(EmployeeRequest employeeRequest) {
//        System.out.println("Gender char before = " + employeeRequest.getGender());
        employeeRequest.setGender(Character.toUpperCase(employeeRequest.getGender()));
//        System.out.println("Gender char after = " + employeeRequest.getGender());
        EmployeeRest newEmployee = new EmployeeRest();
        newEmployee.setBirthDate(employeeRequest.getBirthDate());
        newEmployee.setFirstName(employeeRequest.getFirstName());
        newEmployee.setLastName(employeeRequest.getLastName());
        newEmployee.setGender(employeeRequest.getGender());
        newEmployee.setHireDate(employeeRequest.getHireDate());
        return employeeRepository.save(newEmployee);
    }

    // update an Employee record
    public EmployeeRest updateEmployee(Integer emp_no, EmployeeRequest updateRequest) {
        EmployeeRest storedEmployee = employeeRepository.findById(emp_no).orElse(null);
        if (storedEmployee != null) {
            if (updateRequest.getBirthDate() != null && !(updateRequest.getBirthDate().equals(storedEmployee.getBirthDate()))) {
                storedEmployee.setBirthDate(updateRequest.getBirthDate());
            }
            if (updateRequest.getFirstName() != null && !(updateRequest.getFirstName().equalsIgnoreCase(storedEmployee.getFirstName()))) {
                storedEmployee.setFirstName(updateRequest.getFirstName());
            }
            if (updateRequest.getLastName() != null && !(updateRequest.getLastName().equalsIgnoreCase(storedEmployee.getLastName()))) {
                storedEmployee.setLastName(updateRequest.getLastName());
            }
            if (updateRequest.getGender() != null) {
                updateRequest.setGender(Character.toUpperCase(updateRequest.getGender()));
                if (!(updateRequest.getGender().equals(storedEmployee.getGender()))) {
                    storedEmployee.setGender(updateRequest.getGender());
                }
            }
            if (updateRequest.getHireDate() != null && !(updateRequest.getHireDate().equals(storedEmployee.getHireDate()))) {
                storedEmployee.setHireDate(updateRequest.getHireDate());
            }

            return employeeRepository.save(storedEmployee);
        } else {
            return null;
        }
    }

    // delete an Employee record
    public short deleteEmployee(Integer emp_no) {
        if (employeeRepository.findById(emp_no).orElse(null) != null) {
            employeeRepository.deleteById(emp_no);
            return 204;
        } else {
            return 404;
        }
    }
}
