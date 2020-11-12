package lv.learning.spring_boot_with_mysql.repository;

import lv.learning.spring_boot_with_mysql.model.EmployeeRest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeRest, Integer>, JpaSpecificationExecutor<EmployeeRest> {
//    List<EmployeeRest> findByGender(Character gender);
//
//    List<EmployeeRest> findByHireDateAfter(LocalDate hireAfter);
//
//    List<EmployeeRest> findByHireDateBefore(LocalDate hiredBefore);
}
