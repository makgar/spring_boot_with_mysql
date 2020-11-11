package lv.learning.spring_boot_with_mysql.specification;

import lv.learning.spring_boot_with_mysql.model.EmployeeRest;
import lv.learning.spring_boot_with_mysql.model.EmployeeRest_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public final class EmployeeSpecification {

//    private EmployeeSpecification() {}

    public static Specification<EmployeeRest> hasGender(Character gender) {
        return new Specification<EmployeeRest>() {
            @Override
            public Predicate toPredicate(Root<EmployeeRest> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
//                String filter = getGenderFilter(gender);
                return cb.equal(root.get(EmployeeRest_.gender), gender);
            }
        };
    }

    public static Specification<EmployeeRest> hiredAfter(LocalDate hiredAfter) {
        return new Specification<EmployeeRest>() {
            @Override
            public Predicate toPredicate(Root<EmployeeRest> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.<LocalDate>get(EmployeeRest_.hireDate), hiredAfter);
            }
        };
    }

    public static Specification<EmployeeRest> hiredBefore(LocalDate hiredBefore) {
        return new Specification<EmployeeRest>() {
            @Override
            public Predicate toPredicate(Root<EmployeeRest> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThan(root.<LocalDate>get(EmployeeRest_.hireDate), hiredBefore);
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
