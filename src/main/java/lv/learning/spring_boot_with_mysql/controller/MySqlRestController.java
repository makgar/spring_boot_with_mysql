package lv.learning.spring_boot_with_mysql.controller;

import lv.learning.spring_boot_with_mysql.model.Employee;
import lv.learning.spring_boot_with_mysql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MySqlRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/employees")
    public @ResponseBody Iterable<Employee> readAllEmployees() {
        return employeeService.readAllEmployees();
    }

    // does not work atm
//    @GetMapping(path = "/employees", params = { "page", "size" })
//    public @ResponseBody Iterable<Employee> getEmployeesPaginated(@RequestParam("page") int page,
//                                                                  @RequestParam("size") int size,
//                                                                  UriComponentsBuilder uriComponentsBuilder,
//                                                                  HttpServletResponse httpServletResponse) {
//        Page<Employee> resultPage = employeeService.findPaginated(page, size);
//        return Collections.emptyList();
//    }

    @GetMapping("/employees/{emp_no}")
    public Employee readEmployee(@PathVariable Integer emp_no) {
        return employeeService.readEmployee(emp_no);
    }

    @PostMapping("/employees")
    public void createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
    }

    @PutMapping("/employees/{emp_no}")
    public void updateEmployee(@RequestBody Employee employee, @PathVariable Integer emp_no) {
        employeeService.updateEmployee(emp_no, employee);
    }

    @DeleteMapping("/employees/{emp_no}")
    public void deleteEmployee(@PathVariable Integer emp_no) {
        employeeService.deleteEmployee(emp_no);
    }
}
