package demo.backend.ManageInternship.serviceImpl.userInfo;

import demo.backend.ManageInternship.model.entity.Staff;
import demo.backend.ManageInternship.model.entity.Status;
import demo.backend.ManageInternship.model.payload.request.userInfo.StaffRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.userInfo.StaffResponse;
import demo.backend.ManageInternship.repository.PositionRepository;
import demo.backend.ManageInternship.repository.StaffRepository;
import demo.backend.ManageInternship.repository.StatusRepository;
import demo.backend.ManageInternship.service.userInfo.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    StaffRepository staffRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    PositionRepository positionRepository;
    MessageResponse messageResponse = new MessageResponse();
    @Override
    public ResponseEntity<StaffResponse> getAllStaffUsers(Integer staffId, String staffCode, String staffName, String staffLastName,String staffPosition) {
        try{
            StaffResponse staffResponse = new StaffResponse();
            staffResponse.setStaffList(staffRepository.getStaffList(staffId,staffCode,staffName,staffLastName,staffPosition));
            return new ResponseEntity<>(staffResponse,HttpStatus.OK);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> insertStaffUser(StaffRequest staffRequest) {
       try{
           Staff staff = new Staff();
           Integer n = staffRepository.countStaffPosition(staffRequest.getStaffPosition());
           staff.setStaffCode(staffRequest.getStaffPosition()+ String.format("%0" + 3 + "d", n+1));
           staff.setStaffName(staffRequest.getStaffName());
           staff.setStaffLastname(staffRequest.getStaffLastName());
           staff.setStaffTitle(staffRequest.getStaffTitle());
           staff.setPosId(positionRepository.findByPositionCode(staffRequest.getStaffPosition()));
           staff.setStatusInfo(statusRepository.findByStatusCode("AC"));
           staffRepository.save(staff);
           messageResponse.setStatus("Success");
           messageResponse.setMessage("Successfully insert staff");
           return new ResponseEntity<>(messageResponse,HttpStatus.OK);
       }catch (Exception e){
           messageResponse.setStatus("Failed");
           messageResponse.setMessage("Failed insert staff");
           return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
       }
    }

    @Override
    public ResponseEntity<MessageResponse> updateStaffUser(StaffRequest staffRequest) {
        try{
            Staff staff = staffRepository.findById(staffRequest.getStaffId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
            if(staffRequest.getStaffPosition() != null && !staff.getPosId().getPosCode().equals(staffRequest.getStaffPosition())) {
                Status status = statusRepository.findByStatusCode("IN");
                staff.setStatusInfo(status);
                staffRepository.save(staff);
                Staff newStaff = new Staff();
                Integer n = staffRepository.countStaffPosition(staffRequest.getStaffPosition());
                newStaff.setStaffCode(staffRequest.getStaffPosition()+ String.format("%0" + 3 + "d", n+1));
                newStaff.setStaffName(staffRequest.getStaffName());
                newStaff.setStaffLastname(staffRequest.getStaffLastName());
                newStaff.setStaffTitle(staffRequest.getStaffTitle());
                newStaff.setPosId(positionRepository.findByPositionCode(staffRequest.getStaffPosition()));
                newStaff.setStatusInfo(statusRepository.findByStatusCode("AC"));
                staffRepository.save(newStaff);
            }
            else {
                if (staffRequest.getStaffTitle() != null)
                    staff.setStaffTitle(staffRequest.getStaffTitle());
                if (staffRequest.getStaffName() != null)
                    staff.setStaffName(staffRequest.getStaffName());
                if (staffRequest.getStaffLastName() != null)
                    staff.setStaffLastname(staffRequest.getStaffLastName());
//            Status status = new Status();
//            if(adminRequest.getStatusInfo() != null) {
//                 status = statusRepository.findByStatusCode(adminRequest.getStatusInfo());
//                admin.setStatusInfo(status);
//            }
//            admin.setStatusInfo(status);
                staffRepository.save(staff);
            }
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully update staff");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed update staff");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> deleteStaffUser(StaffRequest staffRequest) {
        try{
            Staff staff = staffRepository.findById(staffRequest.getStaffId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
            Status status = statusRepository.findByStatusCode("IN");
            if(status == null)
            {
                messageResponse.setStatus("Failed");
                messageResponse.setMessage("Failed delete staff");
                return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
            }
            staff.setStatusInfo(status);
            staffRepository.save(staff);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully delete staff");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed delete staff");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }
}
