package lv.learning.spring_boot_with_mysql.controller;

import lv.learning.spring_boot_with_mysql.model.EmployeeRequest;
import lv.learning.spring_boot_with_mysql.model.EmployeeRest;
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

    @GetMapping
    public Page<EmployeeRest> readAllEmployees(@RequestParam(value = "gender", required = false) Character gender,
                                               @RequestParam(value = "hiredAfter", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hiredAfter,
                                               @RequestParam(value = "hiredBefore", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hiredBefore,
                                               @SortDefault(sort = "lastName", direction = Sort.Direction.ASC) Sort sort,
                                               Pageable pageRequest) {
        return employeeService.readEmployees(gender, hiredAfter, hiredBefore, sort, pageRequest);
    }

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
