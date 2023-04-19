package demo.backend.ManageInternship.service;

import demo.backend.ManageInternship.model.payload.request.userInfo.StaffRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.userInfo.StaffResponse;
import org.springframework.http.ResponseEntity;

public interface StaffService {
    ResponseEntity<StaffResponse> getAllStaffUsers(Integer staffId, String staffCode, String staffName, String staffLastName,String staffPosition);

    ResponseEntity<MessageResponse> insertStaffUser(StaffRequest staffRequest);

    ResponseEntity<MessageResponse> updateStaffUser(StaffRequest staffRequest);

    ResponseEntity<MessageResponse> deleteStaffUser(StaffRequest staffRequest);
}
