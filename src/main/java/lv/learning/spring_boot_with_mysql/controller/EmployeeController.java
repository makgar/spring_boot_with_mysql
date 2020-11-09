package lv.learning.spring_boot_with_mysql.controller;

import lv.learning.spring_boot_with_mysql.model.EmployeeRequest;
import lv.learning.spring_boot_with_mysql.model.EmployeeRest;
import lv.learning.spring_boot_with_mysql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/employees", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // temporarily commented out
//    @GetMapping
//    public @ResponseBody Iterable<EmployeeRest> readAllEmployees() {
//        return employeeService.readAllEmployees();
//    }

    @GetMapping
    public Iterable<EmployeeRest> readAllEmployees(@RequestParam(value="start", defaultValue = "1", required = false) int start,
                                                   @RequestParam(value="limit", defaultValue = "2147483647", required = false) int limit) {
        return employeeService.readAllEmployees();
    }

    // does not work atm
//    @GetMapping(path = "/employees", params = { "page", "size" })
//    public @ResponseBody Iterable<EmployeeRest> getEmployeesPaginated(@RequestParam("page") int page,
//                                                                  @RequestParam("size") int size,
//                                                                  UriComponentsBuilder uriComponentsBuilder,
//                                                                  HttpServletResponse httpServletResponse) {
//        Page<EmployeeRest> resultPage = employeeService.findPaginated(page, size);
//        return Collections.emptyList();
//    }

    @GetMapping(path = "/{emp_no}")
    public EmployeeRest readEmployee(@PathVariable Integer emp_no) {
        return employeeService.readEmployee(emp_no);
    }

    @PostMapping
    public EmployeeRest createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {
        return employeeService.createEmployee(employeeRequest);
    }

    @PutMapping(path = "/{emp_no}")
    public EmployeeRest updateEmployee(@RequestBody EmployeeRequest employeeRequest, @PathVariable Integer emp_no) {
        return employeeService.updateEmployee(emp_no, employeeRequest);
    }

    @DeleteMapping(path = "/{emp_no}")
    public void deleteEmployee(@PathVariable Integer emp_no) {
        employeeService.deleteEmployee(emp_no);
    }
}
