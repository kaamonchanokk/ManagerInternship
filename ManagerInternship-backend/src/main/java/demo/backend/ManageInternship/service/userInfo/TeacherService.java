package demo.backend.ManageInternship.service.userInfo;

import demo.backend.ManageInternship.model.payload.request.userInfo.TeacherRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.userInfo.TeacherResponse;
import org.springframework.http.ResponseEntity;

public interface TeacherService {
    ResponseEntity<TeacherResponse> getTeacherList(String teacherCode, String teacherName, String teacherLastName);

    ResponseEntity<MessageResponse> insertTeacher(TeacherRequest teacherRequest);

    ResponseEntity<MessageResponse> updateTeacher(TeacherRequest teacherRequest);

    ResponseEntity<MessageResponse> deleteTeacher(TeacherRequest teacherRequest);
}
