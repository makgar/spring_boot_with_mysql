package lv.learning.spring_boot_with_mysql.controller;

import lv.learning.spring_boot_with_mysql.model.response.EmployeeResponseModel;
import lv.learning.spring_boot_with_mysql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // temporarily commented out
//    @GetMapping
//    public @ResponseBody Iterable<Employee> readAllEmployees() {
//        return employeeService.readAllEmployees();
//    }

    @GetMapping
    public Iterable<EmployeeResponseModel> readAllEmployees(@RequestParam(value="start") int start,
                                                            @RequestParam(value="limit") int limit) {
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

    @GetMapping(path = "/{emp_no}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public EmployeeResponseModel readEmployee(@PathVariable Integer emp_no) {
        return employeeService.readEmployee(emp_no);
    }

    @PostMapping
    public void createEmployee(@RequestBody EmployeeResponseModel employeeResponseModel) {
        employeeService.createEmployee(employeeResponseModel);
    }

    @PutMapping(path = "/{emp_no}")
    public void updateEmployee(@RequestBody EmployeeResponseModel employeeResponseModel, @PathVariable Integer emp_no) {
        employeeService.updateEmployee(emp_no, employeeResponseModel);
    }

    @DeleteMapping(path = "/{emp_no}")
    public void deleteEmployee(@PathVariable Integer emp_no) {
        employeeService.deleteEmployee(emp_no);
    }
}
