package lv.learning.spring_boot_with_mysql.specification;

import lv.learning.spring_boot_with_mysql.model.entity.Employee;
import lv.learning.spring_boot_with_mysql.model.entity.Employee_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public final class EmployeeSpecification {

//    private EmployeeSpecification() {}

    public static Specification<Employee> hasGender(Character gender) {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
//                String filter = getGenderFilter(gender);
                return cb.equal(root.get(Employee_.gender), gender);
            }
        };
    }

    public static Specification<Employee> hiredAfter(LocalDate hiredAfter) {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.<LocalDate>get(Employee_.hireDate), hiredAfter);
            }
        };
    }

    public static Specification<Employee> hiredBefore(LocalDate hiredBefore) {
        return new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.lessThan(root.<LocalDate>get(Employee_.hireDate), hiredBefore);
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
