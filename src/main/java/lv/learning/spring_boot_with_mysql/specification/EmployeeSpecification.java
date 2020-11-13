package lv.learning.spring_boot_with_mysql.specification;

import lv.learning.spring_boot_with_mysql.model.entity.EmployeeEntity;
import lv.learning.spring_boot_with_mysql.model.entity.EmployeeEntity_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public final class EmployeeSpecification {

//    private EmployeeSpecification() {}

    public static Specification<EmployeeEntity> hasGender(Character gender) {
        return new Specification<EmployeeEntity>() {
            @Override
            public Predicate toPredicate(Root<EmployeeEntity> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
//                String filter = getGenderFilter(gender);
                return cb.equal(root.get(EmployeeEntity_.gender), gender);
            }
        };
    }

    public static Specification<EmployeeEntity> hiredAfter(LocalDate hiredAfter) {
        return new Specification<EmployeeEntity>() {
            @Override
            public Predicate toPredicate(Root<EmployeeEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.<LocalDate>get(EmployeeEntity_.hireDate), hiredAfter);
            }
        };
    }

    public static Specification<EmployeeEntity> hiredBefore(LocalDate hiredBefore) {
        return new Specification<EmployeeEntity>() {
            @Override
            public Predicate toPredicate(Root<EmployeeEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThan(root.<LocalDate>get(EmployeeEntity_.hireDate), hiredBefore);
            }
        };
    }

//    private static String getGenderFilter(Character gender) {
//        if (gender == null) {
//            return "%";
//        }
//        else {
//            return gender.toString();
//        }
//    }
}
