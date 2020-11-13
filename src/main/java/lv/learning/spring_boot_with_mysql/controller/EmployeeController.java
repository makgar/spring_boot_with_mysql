package lv.learning.spring_boot_with_mysql.controller;

import lv.learning.spring_boot_with_mysql.model.request.Employee;
import lv.learning.spring_boot_with_mysql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping(path = "/employees", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // commented out and will probably be removed
//    @GetMapping
//    public @ResponseBody Iterable<Employee> readAllEmployees() {
//        return employeeService.readAllEmployees();
//    }

    @GetMapping
    //public Object readAllEmployees(@RequestParam(value="start", defaultValue = "1", required = false) Integer start,
    //@RequestParam(value="limit", defaultValue = "2147483647", required = false) Integer limit,
    //@RequestParam(defaultValue = "lastName,asc") String[] sort,
    public Page<lv.learning.spring_boot_with_mysql.model.entity.Employee> readAllEmployees(@RequestParam(value = "gender", required = false) Character gender,
                                                                                           @RequestParam(value = "hiredAfter", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hiredAfter,
                                                                                           @RequestParam(value = "hiredBefore", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hiredBefore,
                                                                                           @SortDefault(sort = "lastName", direction = Sort.Direction.ASC) Sort sort,
                                                                                           Pageable pageRequest) {
//        return employeeService.readEmployees(start, limit, gender, hiredAfter, hiredBefore, sort);
        return employeeService.readEmployees(gender, hiredAfter, hiredBefore, sort, pageRequest);
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

    @GetMapping(path = "/{emp_no}")
    public ResponseEntity<lv.learning.spring_boot_with_mysql.model.entity.Employee> readEmployee(@PathVariable Integer emp_no) {
        lv.learning.spring_boot_with_mysql.model.entity.Employee employee = employeeService.readEmployee(emp_no);
        if (employee != null) {
            return new ResponseEntity<lv.learning.spring_boot_with_mysql.model.entity.Employee>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<lv.learning.spring_boot_with_mysql.model.entity.Employee> createEmployee(@Valid @RequestBody Employee newEmployee) {
        return new ResponseEntity<lv.learning.spring_boot_with_mysql.model.entity.Employee>(employeeService.createEmployee(newEmployee), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{emp_no}")
    public ResponseEntity<lv.learning.spring_boot_with_mysql.model.entity.Employee> updateEmployee(@RequestBody Employee updateRequest, @PathVariable Integer emp_no) {
        lv.learning.spring_boot_with_mysql.model.entity.Employee updatedEmployee = employeeService.updateEmployee(emp_no, updateRequest);
        if (updatedEmployee != null) {
            return new ResponseEntity<lv.learning.spring_boot_with_mysql.model.entity.Employee>(updatedEmployee, HttpStatus.OK);
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
