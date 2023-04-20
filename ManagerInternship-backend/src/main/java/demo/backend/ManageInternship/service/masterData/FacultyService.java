package demo.backend.ManageInternship.service.masterData;

import demo.backend.ManageInternship.model.payload.request.masterData.FacultyRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.masterData.FacultyResponse;
import org.springframework.http.ResponseEntity;

public interface FacultyService {
    ResponseEntity<FacultyResponse> getFacultyList(String facultyCode, String facultyName);

    ResponseEntity<MessageResponse> insertFaculty(FacultyRequest facultyRequest);

    ResponseEntity<MessageResponse> updateFaculty(FacultyRequest facultyRequest);

    ResponseEntity<MessageResponse> deleteFaculty(FacultyRequest facultyRequest);
}
