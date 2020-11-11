package lv.learning.spring_boot_with_mysql.repository;

import lv.learning.spring_boot_with_mysql.model.EmployeeRest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//@Repository
//public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeRest, Integer> {

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeRest, Integer>, JpaSpecificationExecutor<EmployeeRest> {
    List<EmployeeRest> findFirst10ByGender(Character gender);

    List<EmployeeRest> findFirst10ByHireDateAfter(LocalDate hireAfter);

    List<EmployeeRest> findFirst10ByHireDateBefore(LocalDate hiredBefore);
}
