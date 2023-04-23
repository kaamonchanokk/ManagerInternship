package demo.backend.ManageInternship.controller.userInfo;

import demo.backend.ManageInternship.model.payload.request.userInfo.TeacherRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.userInfo.TeacherResponse;
import demo.backend.ManageInternship.service.userInfo.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userInfo")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/teacherList")
    public ResponseEntity<TeacherResponse> getTeacherList(
            @RequestParam(value = "teacherCode",required = false) String teacherCode,
            @RequestParam(value = "teacherName",required = false) String teacherName,
            @RequestParam(value = "teacherLastName",required = false) String teacherLastName
            ){
        return teacherService.getTeacherList(teacherCode,teacherName,teacherLastName);
    }
    @PostMapping("/insertTeacher")
    public ResponseEntity<MessageResponse> insertTeacher(
            @RequestBody TeacherRequest teacherRequest
    ){
        return teacherService.insertTeacher(teacherRequest);
    }
    @PutMapping("/updateTeacher")
    public ResponseEntity<MessageResponse> updateTeacher(
            @RequestBody TeacherRequest teacherRequest
    ){
        return teacherService.updateTeacher(teacherRequest);
    }
    @PatchMapping("/deleteTeacher")
    public ResponseEntity<MessageResponse> deleteTeacher(
            @RequestBody TeacherRequest teacherRequest
    ){
        return teacherService.deleteTeacher(teacherRequest);
    }
}
