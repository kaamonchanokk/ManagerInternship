package demo.backend.ManageInternship.serviceImpl;

import demo.backend.ManageInternship.model.payload.response.report.*;
import demo.backend.ManageInternship.repository.*;
import demo.backend.ManageInternship.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Override
    public ResponseEntity<TeacherReportResponse> reportTeacher(String teacherCode, String teacherName) {
        try{
            TeacherReportResponse response = new TeacherReportResponse();
            response.setTeacher(teacherRepository.findByCodeOrName(teacherCode,teacherName));
            if(response.getTeacher() == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            response.setStudentList(studentRepository.findByTeacherId(response.getTeacher().getTeacherId()));
            return new ResponseEntity<>(response,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<CompanyReportResponse>> reportCompany(String companyName) {
        try{
            return new ResponseEntity<>(companyRepository.getReportCompany(companyName),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ReportRequestResponse> reportRequest() {
        try{
            ReportRequestResponse response = new ReportRequestResponse();
            response.setReportRequest(requestRepository.getReportRequest());
            return new ResponseEntity<>(response,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ReportYearResponse>> reportYear(String year) {
        try{
            return new ResponseEntity<>(requestRepository.getReportYear(year),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<ReportFacultyResponse>> reportFaculty(String facultyName) {
        try{
            return new ResponseEntity<>(facultyRepository.getReportFaculty(facultyName),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
