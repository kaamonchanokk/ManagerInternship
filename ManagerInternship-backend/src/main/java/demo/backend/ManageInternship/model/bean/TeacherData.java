package demo.backend.ManageInternship.model.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class TeacherData {
    private Integer teacherId;
    private String teacherCode;
    private String teacherName;
    private String statusTeacher;
    private String faculty;

    private String department;

    private String statusInfo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createDate;
    private String createBy;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updateDate;
    private String updateBy;
    public TeacherData(){

    }

    public TeacherData(Integer teacherId, String teacherCode, String teacherName, String statusTeacher, String faculty, String department, String statusInfo, Date createDate, String createBy, Date updateDate, String updateBy) {
        this.teacherId = teacherId;
        this.teacherCode = teacherCode;
        this.teacherName = teacherName;
        this.statusTeacher = statusTeacher;
        this.faculty = faculty;
        this.department = department;
        this.statusInfo = statusInfo;
        this.createDate = createDate;
        this.createBy = createBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
    }
}
