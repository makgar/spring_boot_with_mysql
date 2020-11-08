package lv.learning.spring_boot_with_mysql.repository;

import lv.learning.spring_boot_with_mysql.model.EmployeeRest;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

//@Repository
//public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeRest, Integer> {
@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeRest, Integer> {

}
