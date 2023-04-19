package demo.backend.ManageInternship.controller.userInfo;

import demo.backend.ManageInternship.model.payload.request.userInfo.StaffRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.userInfo.StaffResponse;
import demo.backend.ManageInternship.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userInfo")
public class StaffController {

    @Autowired
    StaffService staffService;
    @GetMapping("/staffList")
    public ResponseEntity<StaffResponse> getAllStaffUsers
            (
                    @RequestParam(value = "staffId",required = false) Integer staffId,
                    @RequestParam(value = "staffCode",required = false) String staffCode,
                    @RequestParam(value = "staffName",required = false) String staffName,
                    @RequestParam(value = "staffLastName",required = false) String staffLastName,
                    @RequestParam(value = "staffPosition",required = false) String staffPosition

            ) {
        return staffService.getAllStaffUsers(staffId,staffCode,staffName,staffLastName,staffPosition);
    }
    @PostMapping("/insertStaff")
    public ResponseEntity<MessageResponse> insertStaffUser(@RequestBody StaffRequest staffRequest) {
        return staffService.insertStaffUser(staffRequest);
    }
    @PutMapping("/updateStaff")
    public ResponseEntity<MessageResponse> updateStaffUser(@RequestBody StaffRequest staffRequest) {
        return staffService.updateStaffUser(staffRequest);
    }
    @PatchMapping("/deleteStaff")
    public ResponseEntity<MessageResponse> deleteStaffUser(@RequestBody StaffRequest staffRequest) {
        return staffService.deleteStaffUser(staffRequest);
    }
}
