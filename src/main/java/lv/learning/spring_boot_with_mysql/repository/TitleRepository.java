package lv.learning.spring_boot_with_mysql.repository;

import lv.learning.spring_boot_with_mysql.model.entity.TitleEntity;
import lv.learning.spring_boot_with_mysql.model.entity.TitlePK;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
/* Temp solution with incorrect primary key
//public interface TitleRepository extends PagingAndSortingRepository<TitleEntity, Integer>, JpaSpecificationExecutor<TitleEntity> {
 */
public interface TitleRepository extends PagingAndSortingRepository<TitleEntity, TitlePK>, JpaSpecificationExecutor<TitleEntity> {
}
