package lv.learning.spring_boot_with_mysql.service;

import lv.learning.spring_boot_with_mysql.model.request.Employee;
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

    // read all Employee records
//    public List<Employee> readAllEmployees() {
////        List<Employee> employees = new ArrayList<>();
////        employeeRepository.findAll().forEach(employees::add);
////        return employees;
//        // shorter version of the same as above
//        return (List<Employee>) employeeRepository.findAll();
//    }

    //    public Iterable<Employee> readEmployees(Integer start, Integer limit, Character gender) {
    //public Object readEmployees(Integer start, Integer limit, Character gender, LocalDate hiredAfter, LocalDate hiredBefore, Sort sort) {
    public Page<lv.learning.spring_boot_with_mysql.model.entity.Employee> readEmployees(Character gender, LocalDate hiredAfter, LocalDate hiredBefore, Sort sort, Pageable pageRequest) {
//        if (gender == null && hiredAfter == null && hiredBefore == null) {
////            List<Employee> employees = new ArrayList<>();
////            employeeRepository.findAll().forEach(employees::add);
////            return employees;
//            // shorter version of the same as above
//            return (List<Employee>) employeeRepository.findAll();
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

        Page<lv.learning.spring_boot_with_mysql.model.entity.Employee> employees = employeeRepository.findAll(Specification
                        .where(gender == null ? null : EmployeeSpecification.hasGender(gender))
                        .and(hiredAfter == null ? null : EmployeeSpecification.hiredAfter(hiredAfter))
                        .and(hiredBefore == null ? null : EmployeeSpecification.hiredBefore(hiredBefore)),
                //sort == null ? defaultOrderBy() : sort,
                paging);

        return employees;
    }

    // does not work atm
//    public List<Employee> findPaginated(Integer pageNo, Integer pageSize) {
//        Pageable paging = PageRequest.of(pageNo, pageSize);
//        Page<Employee> pageResult = employeeRepository.findAll(paging);
//
//        return pageResult.toList();
//    }

    // read a single Employee record
    public lv.learning.spring_boot_with_mysql.model.entity.Employee readEmployee(Integer emp_no) {
//        Employee storedEmployee = employeeRepository.findById(emp_no).orElse(null);
//        return storedEmployee;
        return employeeRepository.findById(emp_no).orElse(null);
    }

    // create new Employee record
    public lv.learning.spring_boot_with_mysql.model.entity.Employee createEmployee(Employee employee) {
//        System.out.println("Gender char before = " + employee.getGender());
        employee.setGender(Character.toUpperCase(employee.getGender()));
//        System.out.println("Gender char after = " + employee.getGender());
        lv.learning.spring_boot_with_mysql.model.entity.Employee newEmployee = new lv.learning.spring_boot_with_mysql.model.entity.Employee();
        newEmployee.setBirthDate(employee.getBirthDate());
        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setLastName(employee.getLastName());
        newEmployee.setGender(employee.getGender());
        newEmployee.setHireDate(employee.getHireDate());
        return employeeRepository.save(newEmployee);
    }

    // update an Employee record
    public lv.learning.spring_boot_with_mysql.model.entity.Employee updateEmployee(Integer emp_no, Employee updateRequest) {
        lv.learning.spring_boot_with_mysql.model.entity.Employee storedEmployee = employeeRepository.findById(emp_no).orElse(null);
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

//    private Sort defaultOrderBy() {
//        System.out.println("marker!");
//        Sort sort = Sort.by(Sort.Direction.ASC, "lastName");
//        return sort;
//    }
}
