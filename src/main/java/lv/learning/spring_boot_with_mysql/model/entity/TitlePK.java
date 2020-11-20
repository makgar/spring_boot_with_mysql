package lv.learning.spring_boot_with_mysql.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class TitlePK implements Serializable {

//    @Column(name = "emp_no")
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
    private EmployeeEntity employeeEntity;
//    private Integer empNo;
    @Column
    private String title;
    @Column(name = "from_date")
    private LocalDate fromDate;

//    public Integer getEmpNo() {
//        return empNo;
//    }
//
//    public void setEmpNo(Integer empNo) {
//        this.empNo = empNo;
//    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public void setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }
}
