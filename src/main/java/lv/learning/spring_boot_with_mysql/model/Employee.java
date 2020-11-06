package lv.learning.spring_boot_with_mysql.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO) //TABLE or may be IDENTITY
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
    //@NotNull
    private Integer emp_no;
    @NotNull
    private String birth_date;
    @NotNull
    @Size(min = 1, max=14)
    private String first_name;
    @NotNull
    @Size(min = 1, max=16)
    private String last_name;
    @NotNull
    private Character gender;
    @NotNull
    private String hire_date;

//    public EmployeeResponseModel() {
//
//    }
//
//    public EmployeeResponseModel(Integer emp_no, String birth_date, String first_name, String last_name, Character gender, String hire_date) {
//        this.emp_no = emp_no;
//        this.birth_date = birth_date;
//        this.first_name = first_name;
//        this.last_name = last_name;
//        this.gender = gender;
//        this.hire_date = hire_date;
//    }

    public Integer getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(Integer emp_no) {
        this.emp_no = emp_no;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }
}
