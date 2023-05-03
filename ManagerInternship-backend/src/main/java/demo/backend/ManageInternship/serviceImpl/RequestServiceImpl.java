package demo.backend.ManageInternship.serviceImpl;

import demo.backend.ManageInternship.model.payload.request.RequestOfInternRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.RequestOfInternResponse;
import demo.backend.ManageInternship.repository.RequestRepository;
import demo.backend.ManageInternship.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    RequestRepository requestRepository;

    MessageResponse messageResponse = new MessageResponse();
    @Override
    public ResponseEntity<RequestOfInternResponse> getRequestList(Date dateRequestFrom, Date dateRequestTo, String studentCode, String studentName, String companyName, String statusApprove, String approveBy) {
        try{
            RequestOfInternResponse requestResponse = new RequestOfInternResponse();
            requestResponse.setRequestList(requestRepository.getRequestList(dateRequestFrom,dateRequestTo,studentCode,studentName,companyName,statusApprove,approveBy));
            return new ResponseEntity<>(requestResponse,HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> insertRequest(RequestOfInternRequest requestOfInternRequest) {
        try{
            requestRepository.insertRequest(requestOfInternRequest);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully insert request");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);

        } catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed insert request");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> updateRequest(RequestOfInternRequest requestOfInternRequest) {
        try{
            String status;
            status = requestRepository.checkStatusId(requestOfInternRequest.getRequestId());
            if(status.equals("AP")){
                messageResponse.setStatus("Failed");
                messageResponse.setMessage("Cannot update request status approve");
                return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
            }
            requestRepository.updateRequest(requestOfInternRequest);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully update request");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        } catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed update request");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> deleteRequest(RequestOfInternRequest requestOfInternRequest) {
        try{
            requestRepository.deleteRequest(requestOfInternRequest);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully delete request");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        } catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed delete request");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> updateStatusRequest(RequestOfInternRequest requestOfInternRequest) {
        try{
            requestRepository.updateStatusRequest(requestOfInternRequest);

            if(requestOfInternRequest.getStatusApprove().equals("AP"))
            {
                messageResponse.setStatus("Success");
                messageResponse.setMessage("Successfully Approve request");
            }

            if(requestOfInternRequest.getStatusApprove().equals("DA"))
            {
                messageResponse.setStatus("Success");
                messageResponse.setMessage("Successfully DisApprove request");
            }
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        } catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed Update StatusApprove request");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }
}
