package demo.backend.ManageInternship.service;

import demo.backend.ManageInternship.model.entity.Admin;
import demo.backend.ManageInternship.model.payload.request.userInfo.AdminRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {
    ResponseEntity<List<Admin>> getAllAdminUsers();

    ResponseEntity<MessageResponse> insertAdminUser(AdminRequest adminRequest);

    ResponseEntity<MessageResponse> updateAdminUser(AdminRequest adminRequest);

    ResponseEntity<MessageResponse> deleteAdminUser(AdminRequest adminRequest);
}
