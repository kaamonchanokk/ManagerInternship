package demo.backend.ManageInternship.controller.masterData;

import demo.backend.ManageInternship.model.payload.request.masterData.FacultyRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.masterData.FacultyResponse;
import demo.backend.ManageInternship.service.masterData.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/masterData")
public class FacultyController {

    @Autowired
    FacultyService facultyService;


    @GetMapping("/facultyList")
    public ResponseEntity<FacultyResponse> getFacultyList(
            @RequestParam(value = "facultyCode",required = false) String facultyCode,
            @RequestParam(value = "facultyName",required = false) String facultyName
            )
    {
        return facultyService.getFacultyList(facultyCode,facultyName);
    }

    @PostMapping("/insertFaculty")
    public ResponseEntity<MessageResponse> insertFaculty(@RequestBody FacultyRequest facultyRequest){
        return facultyService.insertFaculty(facultyRequest);
    }

    @PutMapping("/updateFaculty")
    public ResponseEntity<MessageResponse> updateFaculty(@RequestBody FacultyRequest facultyRequest){
        return facultyService.updateFaculty(facultyRequest);
    }
    @PatchMapping("/deleteFaculty")
    public ResponseEntity<MessageResponse> deleteFaculty(@RequestBody FacultyRequest facultyRequest){
        return facultyService.deleteFaculty(facultyRequest);
    }
}
