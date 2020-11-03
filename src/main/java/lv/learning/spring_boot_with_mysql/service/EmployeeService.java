package lv.learning.spring_boot_with_mysql.service;

import lv.learning.spring_boot_with_mysql.model.Employee;
import lv.learning.spring_boot_with_mysql.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // local mockup collection for testing purposes
    private List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee(1, "date1", "Max", "Garipov", 'M', "date2"),
            new Employee(2, "date1", "Alina", "Garipova", 'F', "date2")
    ));

    public List<Employee> readAllEmployees() {
        // temp mockup
        return employees;
        // returning all Employee records from db table
//        List<Employee> employees = new ArrayList<>();
//        employeeRepository.findAll().forEach(employees::add);
//        return employees;
        // shorter version of the same as above
//        return (List<Employee>) employeeRepository.findAll();
    }

    // does not work atm
//    public List<Employee> findPaginated(int pageNo, int pageSize) {
//        Pageable paging = PageRequest.of(pageNo, pageSize);
//        Page<Employee> pageResult = employeeRepository.findAll(paging);
//
//        return pageResult.toList();
//    }

    public Employee readEmployee(Integer emp_no) {
        // temp mockup
        return employees.stream().filter(employee -> employee.getEmp_no().equals(emp_no)).findFirst().get();
//        Employee employee = employeeRepository.findById(emp_no).orElse(null);
//        return employee;
    }

    public void createEmployee(Employee employee) {
        // temp mockup
        employees.add(employee);
        // to do
    }

    public void updateEmployee(Integer emp_no, Employee employee) {
        // temp mockup
        for (int i = 0; i < employees.size(); i++) {
            Employee e = employees.get(i);
            if (e.getEmp_no().equals(emp_no)) {
                employees.set(i, employee);
                return;
            }
        }
        // to do
    }

    public void deleteEmployee(Integer emp_no) {
        // temp mockup
        employees.removeIf(e -> e.getEmp_no().equals(emp_no));
        // to do
    }
}
