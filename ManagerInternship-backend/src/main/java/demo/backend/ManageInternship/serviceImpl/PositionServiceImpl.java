package demo.backend.ManageInternship.serviceImpl;

import demo.backend.ManageInternship.model.entity.Position;
import demo.backend.ManageInternship.model.entity.Status;
import demo.backend.ManageInternship.model.payload.request.masterData.PositionRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.masterData.PositionResponse;
import demo.backend.ManageInternship.repository.PositionRepository;
import demo.backend.ManageInternship.repository.StaffRepository;
import demo.backend.ManageInternship.repository.StatusRepository;
import demo.backend.ManageInternship.service.PositionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Date;
@Log4j2
@Service
public class PositionServiceImpl implements PositionService {

    MessageResponse messageResponse = new MessageResponse();
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    StatusRepository statusRepository;

    @Autowired
    StaffRepository staffRepository;
    @Override
    public ResponseEntity<PositionResponse> getPositionList(String positionCode, String positionName) {
        try{
            PositionResponse positionResponse = new PositionResponse();
            positionResponse.setPositionList(positionRepository.getPositionList(positionCode,positionName));
            return new ResponseEntity<>(positionResponse,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> insertPosition(PositionRequest positionRequest) {
        try{
            Position position = new Position();
            position.setPosCode(positionRequest.getPositionCode());
            position.setPosName(positionRequest.getPositionName());
            position.setStatusInfo(statusRepository.findByStatusCode("AC"));
            position.setCreateDate(new Date());
            position.setCreateBy(staffRepository.findById(positionRequest.getCreateBy()).orElseThrow(() -> new EntityNotFoundException("User not found")));
            positionRepository.save(position);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully Insert Position");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);

        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed insert position");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<MessageResponse> updatePosition(PositionRequest positionRequest) {
        try{
            Position position = positionRepository.findById(positionRequest.getPositionId()).orElseThrow(() -> new EntityNotFoundException("Position not found"));
            if(positionRequest.getPositionCode() != null)
                position.setPosCode(positionRequest.getPositionCode());
            if(positionRequest.getPositionName() != null)
                position.setPosName(positionRequest.getPositionName());
            position.setUpdateDate(new Date());
            position.setUpdateBy(staffRepository.findById(positionRequest.getUpdateBy()).orElseThrow(() -> new EntityNotFoundException("User not found")));
            positionRepository.save(position);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully update Position");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e)
        {
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed update position");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> deletePosition(PositionRequest positionRequest) {
        try{
            Position position = positionRepository.findById(positionRequest.getPositionId()).orElseThrow(() -> new EntityNotFoundException("Position not found"));
            position.setStatusInfo(statusRepository.findByStatusCode("IN"));
            position.setUpdateDate(new Date());
            position.setUpdateBy(staffRepository.findById(positionRequest.getUpdateBy()).orElseThrow(() -> new EntityNotFoundException("User not found")));
            positionRepository.save(position);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully delete Position");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e)
        {
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed delete position");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }
}
