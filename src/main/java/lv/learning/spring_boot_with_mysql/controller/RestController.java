package lv.learning.spring_boot_with_mysql.controller;

import lv.learning.spring_boot_with_mysql.model.Employee;
import lv.learning.spring_boot_with_mysql.repository.EmployeeRepository;
import lv.learning.spring_boot_with_mysql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/employees")
    public @ResponseBody Iterable<Employee> getAllEmployees() {
        return employeeService.findAll();
    }
}
