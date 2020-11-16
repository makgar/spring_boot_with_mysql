package lv.learning.spring_boot_with_mysql.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "titles")
public class TitleEntity {

    /* Temp solution with incorrect primary key
//    @Id
//    @Column(name = "emp_no")
//    private Integer empNo;
//    @Column
//    private String title;
//    @Column(name = "from_date")
//    private LocalDate fromDate;
     */

    // @AttributeOverride can be switched to @Column in the EmbeddedId class definition
//    @AttributeOverride(name = "empNo", column = @Column(name = "emp_no"))
//    @AttributeOverride(name = "title", column = @Column(name = "title"))
//    @AttributeOverride(name = "fromDate", column = @Column(name = "from_date"))
    @EmbeddedId
    private TitlePK titlePk;
    @Column(name = "to_date")
    private LocalDate toDate;

    /* Temp solution with incorrect primary key
    //    public Integer getEmpNo() {
//        return empNo;
//    }
//
//    public void setEmpNo(Integer empNo) {
//        this.empNo = empNo;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public LocalDate getFromDate() {
//        return fromDate;
//    }
//
//    public void setFromDate(LocalDate fromDate) {
//        this.fromDate = fromDate;
//    }
     */

    public TitlePK getTitlePk() {
        return titlePk;
    }

    public void setTitlePk(TitlePK titlePk) {
        this.titlePk = titlePk;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
