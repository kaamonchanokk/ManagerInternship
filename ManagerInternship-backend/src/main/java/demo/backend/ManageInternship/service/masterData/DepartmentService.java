package demo.backend.ManageInternship.service.masterData;

import demo.backend.ManageInternship.model.payload.request.masterData.DepartmentRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.masterData.DepartmentResponse;
import org.springframework.http.ResponseEntity;

public interface DepartmentService {
    ResponseEntity<DepartmentResponse> getDepartmentList(String depCode, String depName, String facultyName);

    ResponseEntity<MessageResponse> insertDepartment(DepartmentRequest departmentRequest);

    ResponseEntity<MessageResponse> updateDepartment(DepartmentRequest departmentRequest);

    ResponseEntity<MessageResponse> deleteDepartment(DepartmentRequest departmentRequest);
}
