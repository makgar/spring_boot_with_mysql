package lv.learning.spring_boot_with_mysql.controller;

import lv.learning.spring_boot_with_mysql.model.entity.EmployeeEntity;
import lv.learning.spring_boot_with_mysql.model.request.EmployeeRequest;
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
//    public @ResponseBody Iterable<EmployeeEntity> readAllEmployees() {
//        return employeeService.readAllEmployees();
//    }

    @GetMapping
    //public Object readAllEmployees(@RequestParam(value="start", defaultValue = "1", required = false) Integer start,
    //@RequestParam(value="limit", defaultValue = "2147483647", required = false) Integer limit,
    //@RequestParam(defaultValue = "lastName,asc") String[] sort,
    public Page<EmployeeEntity> readEmployees(@RequestParam(value = "gender", required = false) Character gender,
                                              @RequestParam(value = "hiredAfter", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hiredAfter,
                                              @RequestParam(value = "hiredBefore", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hiredBefore,
                                              @SortDefault(sort = "lastName", direction = Sort.Direction.ASC) Sort sort,
                                              Pageable pageRequest) {
//        return employeeService.readEmployees(start, limit, gender, hiredAfter, hiredBefore, sort);
        return employeeService.readEmployees(gender, hiredAfter, hiredBefore, sort, pageRequest);
    }

    // does not work atm
//    @GetMapping(path = "/employees", params = { "page", "size" })
//    public @ResponseBody Iterable<EmployeeEntity> getEmployeesPaginated(@RequestParam("page") int page,
//                                                                  @RequestParam("size") int size,
//                                                                  UriComponentsBuilder uriComponentsBuilder,
//                                                                  HttpServletResponse httpServletResponse) {
//        Page<EmployeeEntity> resultPage = employeeService.findPaginated(page, size);
//        return Collections.emptyList();
//    }

    @GetMapping(path = "/{emp_no}")
    public ResponseEntity<EmployeeEntity> readEmployee(@PathVariable Integer emp_no) {
        EmployeeEntity employeeEntity = employeeService.readEmployee(emp_no);
        if (employeeEntity != null) {
            return new ResponseEntity<EmployeeEntity>(employeeEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeEntity> createEmployee(@Valid @RequestBody EmployeeRequest newEmployeeRequest) {
        return new ResponseEntity<EmployeeEntity>(employeeService.createEmployee(newEmployeeRequest), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{emp_no}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@RequestBody EmployeeRequest updateRequest, @PathVariable Integer emp_no) {
        EmployeeEntity updatedEmployeeEntity = employeeService.updateEmployee(emp_no, updateRequest);
        if (updatedEmployeeEntity != null) {
            return new ResponseEntity<EmployeeEntity>(updatedEmployeeEntity, HttpStatus.OK);
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
                return new ResponseEntity("EmployeeEntity record " + emp_no + " deleted.", HttpStatus.OK);
            case 404:
                return new ResponseEntity("EmployeeEntity record " + emp_no + " not found.", HttpStatus.NOT_FOUND);
            default:
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
