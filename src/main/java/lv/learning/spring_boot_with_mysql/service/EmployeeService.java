package lv.learning.spring_boot_with_mysql.service;

import lv.learning.spring_boot_with_mysql.model.Employee;
import lv.learning.spring_boot_with_mysql.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return (List<Employee>) employeeRepository.findAll();
    }
}
