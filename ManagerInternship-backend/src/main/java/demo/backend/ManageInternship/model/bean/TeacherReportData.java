package demo.backend.ManageInternship.model.bean;

import lombok.Data;

@Data
public class TeacherReportData {
    private Integer teacherId;
    private String teacherCode;
    private String teacherName;
    private String faculty;
    private String department;
    private String status;

    public TeacherReportData() {

    }

    public TeacherReportData(Integer teacherId, String teacherCode, String teacherName, String faculty, String department, String status) {
        this.teacherId = teacherId;
        this.teacherCode = teacherCode;
        this.teacherName = teacherName;
        this.faculty = faculty;
        this.department = department;
        this.status = status;
    }
}
