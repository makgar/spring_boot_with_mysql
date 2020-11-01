package lv.learning.spring_boot_with_mysql.repository;

import org.springframework.data.repository.CrudRepository;

import lv.learning.spring_boot_with_mysql.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
