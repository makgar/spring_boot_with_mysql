package lv.learning.spring_boot_with_mysql.service;

import lv.learning.spring_boot_with_mysql.model.EmployeeRequest;
import lv.learning.spring_boot_with_mysql.model.EmployeeRest;
import lv.learning.spring_boot_with_mysql.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // read all Employee records
    public List<EmployeeRest> readAllEmployees() {
//        List<EmployeeRest> employees = new ArrayList<>();
//        employeeRepository.findAll().forEach(employees::add);
//        return employees;
        // shorter version of the same as above
        return (List<EmployeeRest>) employeeRepository.findAll();
    }

    // does not work atm
//    public List<EmployeeRest> findPaginated(int pageNo, int pageSize) {
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
        newEmployee.setBirth_date(employeeRequest.getBirth_date());
        newEmployee.setFirst_name(employeeRequest.getFirst_name());
        newEmployee.setLast_name(employeeRequest.getLast_name());
        newEmployee.setGender(employeeRequest.getGender());
        newEmployee.setHire_date(employeeRequest.getHire_date());
        return employeeRepository.save(newEmployee);
    }

    // update an Employee record
    public EmployeeRest updateEmployee(Integer emp_no, EmployeeRequest updateRequest) {
        EmployeeRest storedEmployee = employeeRepository.findById(emp_no).orElse(null);
        if (storedEmployee != null) {
            if (updateRequest.getBirth_date() != null && !(updateRequest.getBirth_date().equalsIgnoreCase(storedEmployee.getBirth_date()))) {
                storedEmployee.setBirth_date(updateRequest.getBirth_date());
            }
            if (updateRequest.getFirst_name() != null && !(updateRequest.getFirst_name().equalsIgnoreCase(storedEmployee.getFirst_name()))) {
                storedEmployee.setFirst_name(updateRequest.getFirst_name());
            }
            if (updateRequest.getLast_name() != null && !(updateRequest.getLast_name().equalsIgnoreCase(storedEmployee.getLast_name()))) {
                storedEmployee.setLast_name(updateRequest.getLast_name());
            }
            if (updateRequest.getGender() != null) {
                updateRequest.setGender(Character.toUpperCase(updateRequest.getGender()));
                if (!(updateRequest.getGender().equals(storedEmployee.getGender()))) {
                    storedEmployee.setGender(updateRequest.getGender());
                }
            }
            if (updateRequest.getHire_date() != null && !(updateRequest.getHire_date().equalsIgnoreCase(storedEmployee.getHire_date()))) {
                storedEmployee.setHire_date(updateRequest.getHire_date());
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
        }
        else {
            return 404;
        }
    }
}
