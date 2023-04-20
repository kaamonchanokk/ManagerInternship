package demo.backend.ManageInternship.serviceImpl.masterData;

import demo.backend.ManageInternship.model.entity.Faculty;
import demo.backend.ManageInternship.model.payload.request.masterData.FacultyRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.masterData.FacultyResponse;
import demo.backend.ManageInternship.repository.FacultyRepository;
import demo.backend.ManageInternship.repository.StaffRepository;
import demo.backend.ManageInternship.repository.StatusRepository;
import demo.backend.ManageInternship.service.masterData.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    StatusRepository statusRepository;

    MessageResponse messageResponse = new MessageResponse();

    @Override
    public ResponseEntity<FacultyResponse> getFacultyList(String facultyCode, String facultyName) {
        try{
            FacultyResponse facultyResponse = new FacultyResponse();
            facultyResponse.setFacultyList(facultyRepository.getFacultyList(facultyCode,facultyName));
            return new ResponseEntity<>(facultyResponse,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> insertFaculty(FacultyRequest facultyRequest) {
        try{
            Faculty faculty = new Faculty();
            faculty.setFacultyCode(facultyRequest.getFacultyCode());
            faculty.setFacultyName(facultyRequest.getFacultyName());
            faculty.setStatusInfo(statusRepository.findByStatusCode("AC"));
            faculty.setCreateDate(new Date());
            faculty.setCreateBy(staffRepository.findById(facultyRequest.getCreateBy()).orElseThrow(() -> new EntityNotFoundException("User not found")));
            facultyRepository.save(faculty);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully Insert Faculty");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed Insert Faculty");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> updateFaculty(FacultyRequest facultyRequest) {
        try{
            Faculty faculty = facultyRepository.findById(facultyRequest.getFacultyId()).orElseThrow(()-> new EntityNotFoundException("Faculty not found"));
            if(facultyRequest.getFacultyCode() !=null )
                 faculty.setFacultyCode(facultyRequest.getFacultyCode());
            if(facultyRequest.getFacultyName() !=null )
                 faculty.setFacultyName(faculty.getFacultyName());
            faculty.setUpdateDate(new Date());
            faculty.setUpdateBy(staffRepository.findById(facultyRequest.getUpdateBy()).orElseThrow(() -> new EntityNotFoundException("User not found")));
            facultyRepository.save(faculty);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully Update Faculty");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed Update Faculty");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> deleteFaculty(FacultyRequest facultyRequest) {
        try{
            Faculty faculty = facultyRepository.findById(facultyRequest.getFacultyId()).orElseThrow(()-> new EntityNotFoundException("Faculty not found"));
            faculty.setStatusInfo(statusRepository.findByStatusCode("IN"));
            faculty.setUpdateDate(new Date());
            faculty.setUpdateBy(staffRepository.findById(facultyRequest.getUpdateBy()).orElseThrow(() -> new EntityNotFoundException("User not found")));
            facultyRepository.save(faculty);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully delete Faculty");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed delete Faculty");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }
}
