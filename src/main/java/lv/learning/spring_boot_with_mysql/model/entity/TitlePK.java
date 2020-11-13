package lv.learning.spring_boot_with_mysql.model.entity;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class TitlePK {

    private Integer empNo;
    private String title;
    private LocalDate fromDate;
}
