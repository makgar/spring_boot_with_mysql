package lv.learning.spring_boot_with_mysql.service;

import lv.learning.spring_boot_with_mysql.model.response.EmployeeResponseModel;
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

    public List<EmployeeResponseModel> readAllEmployees() {
        // temp mockup
//        return employees;
        // read all Employee records from DB
//        List<Employee> employees = new ArrayList<>();
//        employeeRepository.findAll().forEach(employees::add);
//        return employees;
        // shorter version of the same as above
        return (List<EmployeeResponseModel>) employeeRepository.findAll();
    }

    // does not work atm
//    public List<Employee> findPaginated(int pageNo, int pageSize) {
//        Pageable paging = PageRequest.of(pageNo, pageSize);
//        Page<Employee> pageResult = employeeRepository.findAll(paging);
//
//        return pageResult.toList();
//    }

    public EmployeeResponseModel readEmployee(Integer emp_no) {
        // temp mockup
//        return employees.stream().filter(employee -> employee.getEmp_no().equals(emp_no)).findFirst().get();
        // read a single Employee record
        EmployeeResponseModel employeeResponseModel = employeeRepository.findById(emp_no).orElse(null);
        return employeeResponseModel;
    }

    public void createEmployee(EmployeeResponseModel employeeResponseModel) {
        // temp mockup
//        employees.add(employee);
        // create new Employee record
        employeeRepository.save(employeeResponseModel);
    }

    public void updateEmployee(Integer emp_no, EmployeeResponseModel employeeResponseModel) {
        // temp mockup
//        for (int i = 0; i < employees.size(); i++) {
//            Employee e = employees.get(i);
//            if (e.getEmp_no().equals(emp_no)) {
//                employees.set(i, employee);
//                return;
//            }
//        }
        // update an Employee record
        if (employeeRepository.findById(emp_no).orElse(null) != null) {
            employeeRepository.save(employeeResponseModel);
        }
    }

    public void deleteEmployee(Integer emp_no) {
        // temp mockup
//        employees.removeIf(e -> e.getEmp_no().equals(emp_no));
        // delete an Employee record
        employeeRepository.deleteById(emp_no);
    }
}
