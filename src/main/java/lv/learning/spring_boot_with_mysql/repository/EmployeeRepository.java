package lv.learning.spring_boot_with_mysql.repository;

import lv.learning.spring_boot_with_mysql.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//@Repository
//public interface EmployeeRepository extends CrudRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
    //    List<Employee> findFirst10ByGender(Character gender);
    List<Employee> findByGender(Character gender);

    //    List<Employee> findFirst10ByHireDateAfter(LocalDate hireAfter);
    List<Employee> findByHireDateAfter(LocalDate hireAfter);

    //    List<Employee> findFirst10ByHireDateBefore(LocalDate hiredBefore);
    List<Employee> findByHireDateBefore(LocalDate hiredBefore);
}
