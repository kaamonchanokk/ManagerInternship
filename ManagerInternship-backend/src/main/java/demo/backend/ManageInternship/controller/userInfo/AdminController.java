package demo.backend.ManageInternship.controller.userInfo;

import demo.backend.ManageInternship.model.entity.Admin;
import demo.backend.ManageInternship.model.payload.request.userInfo.AdminRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userInfo")
public class AdminController {

    @Autowired
    AdminService adminService;

//    @GetMapping("/adminList")
//    public ResponseEntity<List<Admin>> getAllAdminUser() {
//        return adminService.getAllAdminUsers();
//    }
    @PostMapping("/insertAdmin")
    public ResponseEntity<MessageResponse> insertAdminUser(@RequestBody AdminRequest adminRequest) {
        return adminService.insertAdminUser(adminRequest);
    }
    @PutMapping("/updateAdmin")
    public ResponseEntity<MessageResponse> updateAdminUser(@RequestBody AdminRequest adminRequest) {
        return adminService.updateAdminUser(adminRequest);
    }
    @PatchMapping("/deleteAdmin")
    public ResponseEntity<MessageResponse> deleteAdminUser(@RequestBody AdminRequest adminRequest) {
        return adminService.deleteAdminUser(adminRequest);
    }
}
