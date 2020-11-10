package lv.learning.spring_boot_with_mysql.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

public class EmployeeRequest {
    @NotNull
    private LocalDate birth_date;
//    private String birth_date;
    @NotNull
    @Size(min = 1, max = 14)
    private String first_name;
    @NotNull
    @Size(min = 1, max = 16)
    private String last_name;
    @NotNull
    private Character gender;
    @NotNull
    private LocalDate hire_date;
//    private String hire_date;

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
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

    public LocalDate getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDate hire_date) {
        this.hire_date = hire_date;
    }
}
