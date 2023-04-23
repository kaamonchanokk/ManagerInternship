package demo.backend.ManageInternship.serviceImpl.userInfo;

import demo.backend.ManageInternship.model.entity.Student;
import demo.backend.ManageInternship.model.payload.request.userInfo.StudentRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.userInfo.StudentResponse;
import demo.backend.ManageInternship.repository.*;
import demo.backend.ManageInternship.service.userInfo.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class StudentServiceImpl implements StudentService  {
    @Autowired
    StudentRepository studentRepository;
    MessageResponse messageResponse = new MessageResponse();
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    YearRepository  yearRepository;

    @Override
    public ResponseEntity<StudentResponse> getStudentList(String studentCode, String studentName, String studentLastName,String departmentName,String facultyName) {
        try{
            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setStudentList(studentRepository.getStudentList(studentCode, studentName, studentLastName,departmentName,facultyName));
            return new ResponseEntity<>(studentResponse,HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> insertStudent(StudentRequest studentRequest) {
        try{
            Student student = new Student();
            student.setStudentCode(studentRequest.getStudentCode());
            student.setStudentTitle(studentRequest.getStudentTitle());
            student.setStudentName(studentRequest.getStudentName());
            student.setStudentLastname(studentRequest.getStudentLastName());
            student.setStudentGrade(studentRequest.getStudentGrade().doubleValue());
            student.setDepId(departmentRepository.getDepartmentByCode(studentRequest.getDepartmentCode()));
            student.setStatusStudent(statusRepository.findByStatusStudentCode("S"));
            student.setStatusInfo(statusRepository.findByStatusCode("AC"));
            student.setAdvisor(teacherRepository.findByCode(studentRequest.getAdvisorCode()));
            student.setStudentYear(yearRepository.findYearByYear(studentRequest.getStudentYear()));
            student.setCreateDate(new Date());
            student.setCreateBy(staffRepository.findById(studentRequest.getCreateBy()).orElseThrow(()-> new EntityNotFoundException("user not found")));
            studentRepository.save(student);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully insert student");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);

        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed insert student");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> updateStudent(StudentRequest studentRequest) {
        try{
            Student student = studentRepository.findById(studentRequest.getStudentId()).orElseThrow(()->new EntityNotFoundException());
                if(studentRequest.getDepartmentCode() != null)
                    student.setDepId(departmentRepository.getDepartmentByCode(studentRequest.getDepartmentCode()));
                if(studentRequest.getStudentCode() != null)
                    student.setStudentCode(studentRequest.getStudentCode());
                if(studentRequest.getStudentTitle() != null)
                    student.setStudentTitle(studentRequest.getStudentTitle());
                if(studentRequest.getStudentName() != null)
                    student.setStudentName(studentRequest.getStudentName());
                if(studentRequest.getStudentLastName() != null)
                    student.setStudentLastname(studentRequest.getStudentLastName());
                if(studentRequest.getStudentGrade() != null)
                    student.setStudentGrade(studentRequest.getStudentGrade().doubleValue());
                if(studentRequest.getAdvisorCode() != null)
                    student.setAdvisor(teacherRepository.findByCode(studentRequest.getAdvisorCode()));
                student.setUpdateDate(new Date());
                student.setUpdateBy(staffRepository.findById(studentRequest.getUpdateBy()).orElseThrow(()-> new EntityNotFoundException("user not found")));
                studentRepository.save(student);


            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully update student");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);

        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed update student");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> deleteStudent(StudentRequest studentRequest) {
        try{
            Student student = studentRepository.findById(studentRequest.getStudentId()).orElseThrow(()->new EntityNotFoundException());
            student.setStatusInfo(statusRepository.findByStatusCode("AC"));
            student.setUpdateDate(new Date());
            student.setUpdateBy(staffRepository.findById(studentRequest.getUpdateBy()).orElseThrow(()-> new EntityNotFoundException("user not found")));
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully delete student");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);

        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed delete student");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }
}
