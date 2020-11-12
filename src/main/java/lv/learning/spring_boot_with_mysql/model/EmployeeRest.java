package lv.learning.spring_boot_with_mysql.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import org.hibernate.annotations.Parameter;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class EmployeeRest {
    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO) //TABLE or IDENTITY
    /* None of the options above worked for me.
    I assume the reason is that an existing pre-populated table with ~400000 records (from emp_no 100000 to emp_no
    500000) is used, which has no in-built ID field population (no AUTO_INCREMENT or anything). Had to use custom
    sequence generator below. Might change later, if I'll figure out how to make any of the basic options work. */
    @GeneratedValue(generator = "emp_no-sequence-generator")
    @GenericGenerator(
            name = "emp_no-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "emp_no_user_sequence"),
                    @Parameter(name = "initial_value", value = "500001"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "emp_no")
    private Integer empNo;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private Character gender;
    @Column(name = "hire_date")
    private LocalDate hireDate;

    public Integer getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Integer empNo) {
        this.empNo = empNo;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
}
