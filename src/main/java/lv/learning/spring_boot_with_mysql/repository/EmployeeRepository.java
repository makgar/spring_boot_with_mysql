package lv.learning.spring_boot_with_mysql.repository;

import org.springframework.data.repository.CrudRepository;

import lv.learning.spring_boot_with_mysql.model.Employee;
import org.springframework.stereotype.Repository;

//@Repository
//public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
