package lv.learning.spring_boot_with_mysql.service;

import lv.learning.spring_boot_with_mysql.model.EmployeeRequest;
import lv.learning.spring_boot_with_mysql.model.EmployeeRest;
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
    public Page<EmployeeRest> readEmployees(Character gender, LocalDate hiredAfter, LocalDate hiredBefore, Sort sort, Pageable pageRequest) {
        Pageable paging = PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize(), sort);

        Page<EmployeeRest> employees = employeeRepository.findAll(Specification
                        .where(gender == null ? null : EmployeeSpecification.hasGender(gender))
                        .and(hiredAfter == null ? null : EmployeeSpecification.hiredAfter(hiredAfter))
                        .and(hiredBefore == null ? null : EmployeeSpecification.hiredBefore(hiredBefore)),
                paging);

        return employees;
    }

    // read a single Employee record
    public EmployeeRest readEmployee(Integer emp_no) {
        return employeeRepository.findById(emp_no).orElse(null);
    }

    // create new Employee record
    public EmployeeRest createEmployee(EmployeeRequest employeeRequest) {
        employeeRequest.setGender(Character.toUpperCase(employeeRequest.getGender()));
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
