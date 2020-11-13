package lv.learning.spring_boot_with_mysql.repository;

import lv.learning.spring_boot_with_mysql.model.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

//@Repository
//public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer>, JpaSpecificationExecutor<EmployeeEntity> {

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Integer>, JpaSpecificationExecutor<EmployeeEntity> {
    //    List<EmployeeEntity> findFirst10ByGender(Character gender);
    List<EmployeeEntity> findByGender(Character gender);

    //    List<EmployeeEntity> findFirst10ByHireDateAfter(LocalDate hireAfter);
    List<EmployeeEntity> findByHireDateAfter(LocalDate hireAfter);

    //    List<EmployeeEntity> findFirst10ByHireDateBefore(LocalDate hiredBefore);
    List<EmployeeEntity> findByHireDateBefore(LocalDate hiredBefore);
}
