//package lv.learning.spring_boot_with_mysql.model.request;
//
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.time.LocalDate;
//
//public class TitleRequest {
//
//    @NotNull
//    @Size(min = 1, max = 50)
//    private String[] title;
//    @NotNull
//    private LocalDate fromDate;
//    // default value = null. Taken from MySQL table definition
//    private LocalDate toDate = null;
//
//    public String[] getTitle() {
//        return title;
//    }
//
//    public void setTitle(String[] title) {
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
//
//    public LocalDate getToDate() {
//        return toDate;
//    }
//
//    public void setToDate(LocalDate toDate) {
//        this.toDate = toDate;
//    }
//}
