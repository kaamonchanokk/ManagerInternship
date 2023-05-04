package demo.backend.ManageInternship.model.payload.response.report;

import demo.backend.ManageInternship.model.bean.StudentReportData;
import demo.backend.ManageInternship.model.bean.TeacherReportData;
import lombok.Data;

import java.util.List;

@Data
public class TeacherReportResponse {
    private TeacherReportData teacher;
    private List<StudentReportData> studentList;
}
