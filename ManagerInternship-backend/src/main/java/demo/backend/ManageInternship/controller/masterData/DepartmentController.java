package demo.backend.ManageInternship.controller.masterData;

import demo.backend.ManageInternship.model.payload.request.masterData.DepartmentRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.masterData.DepartmentResponse;
import demo.backend.ManageInternship.service.masterData.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/masterData")
public class DepartmentController {

    @Autowired
    DepartmentService  departmentService;

    @GetMapping("/departmentList")
    public ResponseEntity<DepartmentResponse> getDepartmentList(
            @RequestParam(value = "depCode",required = false) String depCode,
            @RequestParam(value = "depName",required = false) String depName,
            @RequestParam(value = "facultyName",required = false) String facultyName
    ){
        return departmentService.getDepartmentList(depCode,depName,facultyName);
    }

    @PostMapping("/insertDepartment")
    public ResponseEntity<MessageResponse> insertDepartment(
            @RequestBody DepartmentRequest departmentRequest
    ){
        return departmentService.insertDepartment(departmentRequest);
    }

    @PutMapping("/updateDepartment")
    public ResponseEntity<MessageResponse> updateDepartment(
            @RequestBody DepartmentRequest departmentRequest
    ){
        return departmentService.updateDepartment(departmentRequest);
    }

    @PatchMapping("/deleteDepartment")
    public ResponseEntity<MessageResponse> deleteDepartment(
            @RequestBody DepartmentRequest departmentRequest
    ){
        return departmentService.deleteDepartment(departmentRequest);
    }
}
