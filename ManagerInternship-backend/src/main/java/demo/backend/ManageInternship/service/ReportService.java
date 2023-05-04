package demo.backend.ManageInternship.service;

import demo.backend.ManageInternship.model.payload.response.report.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReportService {
    ResponseEntity<TeacherReportResponse> reportTeacher(String teacherCode, String teacherName);

    ResponseEntity<List<CompanyReportResponse>> reportCompany(String companyName);

    ResponseEntity<ReportRequestResponse> reportRequest();

    ResponseEntity<List<ReportYearResponse>> reportYear(String year);

    ResponseEntity<List<ReportFacultyResponse>> reportFaculty(String facultyName);
}
