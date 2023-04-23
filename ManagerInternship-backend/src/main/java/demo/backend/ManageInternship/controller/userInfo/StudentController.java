package demo.backend.ManageInternship.controller.userInfo;

import demo.backend.ManageInternship.model.payload.request.userInfo.StudentRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.userInfo.StudentResponse;
import demo.backend.ManageInternship.service.userInfo.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userInfo")
public class StudentController {

    @Autowired
    StudentService studentService;


    @GetMapping("/studentList")
    public ResponseEntity<StudentResponse> getStudentList(
            @RequestParam(value = "studentCode",required = false) String studentCode,
            @RequestParam(value = "studentName",required = false) String studentName,
            @RequestParam(value = "studentLastName",required = false) String studentLastName,
            @RequestParam(value = "departmentName",required = false) String departmentName,
            @RequestParam(value = "facultyName",required = false) String facultyName
            ){
        return studentService.getStudentList(studentCode,studentName,studentLastName,departmentName,facultyName);
    }

    @PostMapping("/insertStudent")
    public ResponseEntity<MessageResponse> insertStudent(
            @RequestBody StudentRequest studentRequest
    )
    {
        return studentService.insertStudent(studentRequest);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<MessageResponse> updateStudent(@RequestBody StudentRequest studentRequest)
    {
        return studentService.updateStudent(studentRequest);
    }

    @PatchMapping("/deleteStudent")
    public ResponseEntity<MessageResponse> deleteStudent(@RequestBody StudentRequest studentRequest)
    {
        return studentService.deleteStudent(studentRequest);
    }

}
