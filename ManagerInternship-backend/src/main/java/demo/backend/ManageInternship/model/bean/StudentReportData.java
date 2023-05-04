package demo.backend.ManageInternship.model.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class StudentReportData {
    private Integer studentId;
    private String studentCode;
    private String studentName;
    private BigDecimal studentGrade;
    private String status;

    public StudentReportData() {

    }

    public StudentReportData(Integer studentId, String studentCode, String studentName, BigDecimal studentGrade, String status) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.studentGrade = studentGrade;
        this.status = status;
    }
}
