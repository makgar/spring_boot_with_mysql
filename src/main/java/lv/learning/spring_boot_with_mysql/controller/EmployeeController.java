package lv.learning.spring_boot_with_mysql.controller;

import lv.learning.spring_boot_with_mysql.model.Employee;
import lv.learning.spring_boot_with_mysql.model.EmployeeMock;
import lv.learning.spring_boot_with_mysql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/employees", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // temporarily commented out
//    @GetMapping
//    public @ResponseBody Iterable<Employee> readAllEmployees() {
//        return employeeService.readAllEmployees();
//    }

    @GetMapping//(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public Iterable<Employee> readAllEmployees(@RequestParam(value="start", defaultValue = "1", required = false) int start,
                                               @RequestParam(value="limit", defaultValue = "2147483647", required = false) int limit) {
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

    @GetMapping(path = "/{emp_no}")//(path = "/{emp_no}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public Employee readEmployee(@PathVariable Integer emp_no) {
        return employeeService.readEmployee(emp_no);
    }

    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

//    @PostMapping(path = "/mock")
//    public ResponseEntity<EmployeeMock> createEmployee(@Valid @RequestBody EmployeeMock employeeMock) {
//        EmployeeMock newEmployee = new EmployeeMock();
//        newEmployee.setBirth_date(employeeMock.getBirth_date());
//        newEmployee.setFirst_name(employeeMock.getFirst_name());
//        newEmployee.setLast_name(employeeMock.getLast_name());
//        newEmployee.setGender(employeeMock.getGender());
//        newEmployee.setHire_date(employeeMock.getHire_date());
//        return new ResponseEntity<EmployeeMock>(newEmployee, HttpStatus.OK);
//    }

    @PutMapping(path = "/{emp_no}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Integer emp_no) {
        return employeeService.updateEmployee(emp_no, employee);
    }

    @DeleteMapping(path = "/{emp_no}")
    public void deleteEmployee(@PathVariable Integer emp_no) {
        employeeService.deleteEmployee(emp_no);
    }
}
