package demo.backend.ManageInternship.service;

import demo.backend.ManageInternship.model.payload.request.RequestOfInternRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.RequestOfInternResponse;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface RequestService {
    ResponseEntity<RequestOfInternResponse> getRequestList(Date dateRequestFrom, Date dateRequestTo, String studentCode, String studentName, String companyName, String statusApprove, String approveBy);

    ResponseEntity<MessageResponse> insertRequest(RequestOfInternRequest requestOfInternRequest);

    ResponseEntity<MessageResponse> updateRequest(RequestOfInternRequest requestOfInternRequest);

    ResponseEntity<MessageResponse> deleteRequest(RequestOfInternRequest requestOfInternRequest);

    ResponseEntity<MessageResponse> updateStatusRequest(RequestOfInternRequest requestOfInternRequest);
}
