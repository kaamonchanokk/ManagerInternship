package demo.backend.ManageInternship.controller;

import demo.backend.ManageInternship.model.payload.response.report.*;
import demo.backend.ManageInternship.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping("/reportTeacher")
    public ResponseEntity<TeacherReportResponse> reportTeacher(
            @RequestParam(value="teacherCode",required = true) String teacherCode,
            @RequestParam(value="teacherName",required = false) String teacherName
    ){
        return reportService.reportTeacher(teacherCode, teacherName);
    }
    @GetMapping("/reportCompany")
    public ResponseEntity<List<CompanyReportResponse>> reportCompany(@RequestParam(value = "companyName") String companyName ){
        return reportService.reportCompany(companyName);
    }
    @GetMapping("/reportRequest")
    public ResponseEntity<ReportRequestResponse> reportRequest(){
        return reportService.reportRequest();
    }
    @GetMapping("/reportYear")
    public ResponseEntity<List<ReportYearResponse>> reportYear(@RequestParam(value = "year") String year ){
        return reportService.reportYear(year);
    }
    @GetMapping("/reportFaculty")
    public ResponseEntity<List<ReportFacultyResponse>> reportFaculty(@RequestParam(value = "facultyName") String facultyName )
    {
        return reportService.reportFaculty(facultyName);
    }
}
