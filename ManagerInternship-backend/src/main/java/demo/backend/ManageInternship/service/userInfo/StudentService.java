package demo.backend.ManageInternship.service.userInfo;

import demo.backend.ManageInternship.model.payload.request.userInfo.StudentRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.userInfo.StudentResponse;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<StudentResponse> getStudentList(String studentCode, String studentName, String studentLastName,String departmentName,String facultyName);

    ResponseEntity<MessageResponse> insertStudent(StudentRequest studentRequest);

    ResponseEntity<MessageResponse> updateStudent(StudentRequest studentRequest);

    ResponseEntity<MessageResponse> deleteStudent(StudentRequest studentRequest);
}
