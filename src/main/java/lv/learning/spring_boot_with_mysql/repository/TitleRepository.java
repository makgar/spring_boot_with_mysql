package lv.learning.spring_boot_with_mysql.repository;

import lv.learning.spring_boot_with_mysql.model.entity.TitleEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends PagingAndSortingRepository<TitleEntity, Integer>, JpaSpecificationExecutor<TitleEntity> {
}
