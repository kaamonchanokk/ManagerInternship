package demo.backend.ManageInternship.model.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class StudentData {
    private Integer studentId;
    private String studentCode;
    private String studentName;
    private String studentYear;
    private String facultyName;
    private String departmentName;
    private BigDecimal studentGrade;
    private String statusStudent;
    private String advisorName;
    private String statusInfo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createdDate;
    private String createBy;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updateDate;
    private String updateBy;

    public StudentData(){}

    public StudentData(Integer studentId, String studentCode, String studentName, String studentYear, String facultyName, String departmentName, BigDecimal studentGrade, String statusStudent, String advisorName, String statusInfo, Date createdDate, String createBy, Date updateDate, String updateBy) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.studentYear = studentYear;
        this.facultyName = facultyName;
        this.departmentName = departmentName;
        this.studentGrade = studentGrade;
        this.statusStudent = statusStudent;
        this.advisorName = advisorName;
        this.statusInfo = statusInfo;
        this.createdDate = createdDate;
        this.createBy = createBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
    }
}
