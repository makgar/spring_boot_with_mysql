package lv.learning.spring_boot_with_mysql.controller;

import lv.learning.spring_boot_with_mysql.model.EmployeeRequest;
import lv.learning.spring_boot_with_mysql.model.EmployeeRest;
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

    // commented out and will probably be removed
//    @GetMapping
//    public @ResponseBody Iterable<EmployeeRest> readAllEmployees() {
//        return employeeService.readAllEmployees();
//    }

    @GetMapping
    public Object readAllEmployees(@RequestParam(value="start", defaultValue = "1", required = false) Integer start,
                                                   @RequestParam(value="limit", defaultValue = "2147483647", required = false) Integer limit,
                                                   @RequestParam(value="gender", required = false) Character gender) {
        return employeeService.readEmployees(start, limit, gender);
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
    public ResponseEntity<EmployeeRest> readEmployee(@PathVariable Integer emp_no) {
        EmployeeRest employee = employeeService.readEmployee(emp_no);
        if (employee != null) {
            return new ResponseEntity<EmployeeRest>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeRest> createEmployee(@Valid @RequestBody EmployeeRequest newEmployee) {
        return new ResponseEntity<EmployeeRest>(employeeService.createEmployee(newEmployee), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{emp_no}")
    public ResponseEntity<EmployeeRest> updateEmployee(@RequestBody EmployeeRequest updateRequest, @PathVariable Integer emp_no) {
        EmployeeRest updatedEmployee = employeeService.updateEmployee(emp_no, updateRequest);
        if (updatedEmployee != null) {
            return new ResponseEntity<EmployeeRest>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{emp_no}")
    public ResponseEntity deleteEmployee(@PathVariable Integer emp_no) {
        short statusCode = employeeService.deleteEmployee(emp_no);
        switch (statusCode) {
            case 204:
                //return new ResponseEntity(HttpStatus.NO_CONTENT);
                return new ResponseEntity("Employee record " + emp_no + " deleted.", HttpStatus.OK);
            case 404:
                return new ResponseEntity("Employee record " + emp_no + " not found.", HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
