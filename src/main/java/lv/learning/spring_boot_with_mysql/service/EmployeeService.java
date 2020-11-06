package lv.learning.spring_boot_with_mysql.service;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import lv.learning.spring_boot_with_mysql.model.Employee;
import lv.learning.spring_boot_with_mysql.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // local mockup collection for testing purposes
//    private List<Employee> employees = new ArrayList<>(Arrays.asList(
//            new Employee(1, "date1", "Max", "Garipov", 'M', "date2"),
//            new Employee(2, "date1", "Alina", "Garipova", 'F', "date2")
//    ));

    public List<Employee> readAllEmployees() {
        // temp mockup
//        return employees;
        // read all Employee records from DB
//        List<Employee> employees = new ArrayList<>();
//        employeeRepository.findAll().forEach(employees::add);
//        return employees;
        // shorter version of the same as above
        return (List<Employee>) employeeRepository.findAll();
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
//        return employees.stream().filter(employee -> employee.getEmp_no().equals(emp_no)).findFirst().get();
        // read a single Employee record
        Employee employee = employeeRepository.findById(emp_no).orElse(null);
        return employee;
    }

    public Employee createEmployee(Employee employee) {
        // temp mockup
//        employees.add(employee);
        // create new Employee record
        System.out.println("Gender char before = " + employee.getGender());
        employee.setGender(Character.toUpperCase(employee.getGender()));
        System.out.println("Gender char after = " + employee.getGender());
        System.out.println("Id of the new JSON payload Employee = " + employee.getEmp_no());
        Employee savedEmployee = employeeRepository.save(employee);
        System.out.println("Id of the saved Employee = " + savedEmployee.getEmp_no());
        return savedEmployee;
    }

    public Employee updateEmployee(Integer emp_no, Employee employee) {
        // temp mockup
//        for (int i = 0; i < employees.size(); i++) {
//            Employee e = employees.get(i);
//            if (e.getEmp_no().equals(emp_no)) {
//                employees.set(i, employee);
//                return;
//            }
//        }
        // update an Employee record
        Employee existingEmployee = employeeRepository.findById(emp_no).orElse(null);
        if (existingEmployee != null) {
//            if (employee.getEmp_no() != null && employee.getEmp_no() != existingEmployee.getEmp_no()) {
//                existingEmployee.setEmp_no(employee.getEmp_no());
//            }
            if (employee.getBirth_date() != null && !(employee.getBirth_date().equalsIgnoreCase(existingEmployee.getBirth_date()))) { // change to not equals
                existingEmployee.setBirth_date(employee.getBirth_date());
            }
            if (employee.getFirst_name() != null && !(employee.getFirst_name().equalsIgnoreCase(existingEmployee.getFirst_name()))) {
                existingEmployee.setFirst_name(employee.getFirst_name());
            }
            if (employee.getLast_name() != null && !(employee.getLast_name().equalsIgnoreCase(existingEmployee.getLast_name()))) {
                existingEmployee.setLast_name(employee.getLast_name());
            }
            if (employee.getGender() != null) {
                employee.setGender(Character.toUpperCase(employee.getGender()));
                if (!(employee.getGender().equals(existingEmployee.getGender()))) {
                    existingEmployee.setGender(employee.getGender());
                }
            }
            if (employee.getHire_date() != null && !(employee.getHire_date().equalsIgnoreCase(existingEmployee.getHire_date()))) {
                existingEmployee.setHire_date(employee.getHire_date());
            }
        }
        return employeeRepository.save(existingEmployee); // writes null, change
    }

    public void deleteEmployee(Integer emp_no) {
        // temp mockup
//        employees.removeIf(e -> e.getEmp_no().equals(emp_no));
        // delete an Employee record
        employeeRepository.deleteById(emp_no);
    }
}
