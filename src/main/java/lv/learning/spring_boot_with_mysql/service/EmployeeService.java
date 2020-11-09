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
        EmployeeRest employeeRest = employeeRepository.findById(emp_no).orElse(null);
        return employeeRest;
    }

    // create new Employee record
    public EmployeeRest createEmployee(EmployeeRequest employeeRequest) {
        System.out.println("Gender char before = " + employeeRequest.getGender());
        employeeRequest.setGender(Character.toUpperCase(employeeRequest.getGender()));
        System.out.println("Gender char after = " + employeeRequest.getGender());
        EmployeeRest newEmployeeRest = new EmployeeRest();
        newEmployeeRest.setBirth_date(employeeRequest.getBirth_date());
        newEmployeeRest.setFirst_name(employeeRequest.getFirst_name());
        newEmployeeRest.setLast_name(employeeRequest.getLast_name());
        newEmployeeRest.setGender(employeeRequest.getGender());
        newEmployeeRest.setHire_date(employeeRequest.getHire_date());
        return employeeRepository.save(newEmployeeRest);
    }

    // update an Employee record
    public EmployeeRest updateEmployee(Integer emp_no, EmployeeRequest employeeRequest) {
        EmployeeRest existingEmployeeRest = employeeRepository.findById(emp_no).orElse(null);
        if (existingEmployeeRest != null) {
            if (employeeRequest.getBirth_date() != null && !(employeeRequest.getBirth_date().equalsIgnoreCase(existingEmployeeRest.getBirth_date()))) {
                existingEmployeeRest.setBirth_date(employeeRequest.getBirth_date());
            }
            if (employeeRequest.getFirst_name() != null && !(employeeRequest.getFirst_name().equalsIgnoreCase(existingEmployeeRest.getFirst_name()))) {
                existingEmployeeRest.setFirst_name(employeeRequest.getFirst_name());
            }
            if (employeeRequest.getLast_name() != null && !(employeeRequest.getLast_name().equalsIgnoreCase(existingEmployeeRest.getLast_name()))) {
                existingEmployeeRest.setLast_name(employeeRequest.getLast_name());
            }
            if (employeeRequest.getGender() != null) {
                employeeRequest.setGender(Character.toUpperCase(employeeRequest.getGender()));
                if (!(employeeRequest.getGender().equals(existingEmployeeRest.getGender()))) {
                    existingEmployeeRest.setGender(employeeRequest.getGender());
                }
            }
            if (employeeRequest.getHire_date() != null && !(employeeRequest.getHire_date().equalsIgnoreCase(existingEmployeeRest.getHire_date()))) {
                existingEmployeeRest.setHire_date(employeeRequest.getHire_date());
            }
        }
        return employeeRepository.save(existingEmployeeRest);
    }

    // delete an Employee record
    public void deleteEmployee(Integer emp_no) {
        employeeRepository.deleteById(emp_no);
    }
}
