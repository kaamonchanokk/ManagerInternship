package demo.backend.ManageInternship.serviceImpl;

import demo.backend.ManageInternship.model.entity.Admin;
import demo.backend.ManageInternship.model.entity.Status;
import demo.backend.ManageInternship.model.payload.request.userInfo.AdminRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.userInfo.AdminResponse;
import demo.backend.ManageInternship.repository.AdminRepository;
import demo.backend.ManageInternship.repository.StatusRepository;
import demo.backend.ManageInternship.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    StatusRepository statusRepository;
    MessageResponse messageResponse = new MessageResponse();
    @Override
    public ResponseEntity<AdminResponse> getAllAdminUsers(Integer userId,String userCode,String userName,String userLastName) {
        try{
            AdminResponse adminResponse = new AdminResponse();
            adminResponse.setAdminList(adminRepository.getAdminList(userId,userCode,userName,userLastName));
            return new ResponseEntity<>(adminResponse,HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> insertAdminUser(AdminRequest adminRequest) {
       try{
           Admin admin = new Admin();
           admin.setUserCode(adminRequest.getUserCode());
           admin.setUserName(adminRequest.getUserName());
           admin.setUserLastname(adminRequest.getUserLastName());
           Status status = statusRepository.findById(1).orElseThrow(() -> new EntityNotFoundException("Status not found"));
           admin.setStatusInfo(status);
           adminRepository.save(admin);
           messageResponse.setStatus("Success");
           messageResponse.setMessage("Successfully insert admin");
           return new ResponseEntity<>(messageResponse,HttpStatus.OK);
       }catch (Exception e){
           messageResponse.setStatus("Failed");
           messageResponse.setMessage("Failed insert admin");
           return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
       }
    }

    @Override
    public ResponseEntity<MessageResponse> updateAdminUser(AdminRequest adminRequest) {
        try{
            Admin admin = adminRepository.findById(adminRequest.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
            if(adminRequest.getUserCode() != null)
                admin.setUserCode(adminRequest.getUserCode());
            if(adminRequest.getUserName() != null)
                admin.setUserName(adminRequest.getUserName());
            if(adminRequest.getUserLastName() != null)
                admin.setUserLastname(adminRequest.getUserLastName());
//            Status status = new Status();
//            if(adminRequest.getStatusInfo() != null) {
//                 status = statusRepository.findByStatusCode(adminRequest.getStatusInfo());
//                admin.setStatusInfo(status);
//            }
//            admin.setStatusInfo(status);
            adminRepository.save(admin);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully update admin");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed update admin");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> deleteAdminUser(AdminRequest adminRequest) {
        try{
            Admin admin = adminRepository.findById(adminRequest.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
            Status status = statusRepository.findByStatusCode("IN");
            if(status == null)
            {
                messageResponse.setStatus("Failed");
                messageResponse.setMessage("Failed delete admin");
                return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
            }
            admin.setStatusInfo(status);
            adminRepository.save(admin);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully delete admin");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed delete admin");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }
}
