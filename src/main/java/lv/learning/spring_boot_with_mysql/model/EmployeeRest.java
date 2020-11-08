package lv.learning.spring_boot_with_mysql.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "employees")
public class EmployeeRest {
    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO) //TABLE or IDENTITY
    //Had to use custom sequence generator
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
    private Integer emp_no;
    private String birth_date;
    private String first_name;
    private String last_name;
    private Character gender;
    private String hire_date;

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
