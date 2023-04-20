package demo.backend.ManageInternship.serviceImpl.masterData;

import demo.backend.ManageInternship.model.entity.Department;
import demo.backend.ManageInternship.model.payload.request.masterData.DepartmentRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.masterData.DepartmentResponse;
import demo.backend.ManageInternship.repository.DepartmentRepository;
import demo.backend.ManageInternship.repository.FacultyRepository;
import demo.backend.ManageInternship.repository.StaffRepository;
import demo.backend.ManageInternship.repository.StatusRepository;
import demo.backend.ManageInternship.service.masterData.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    StaffRepository staffRepository;
    MessageResponse messageResponse = new MessageResponse();
    @Override
    public ResponseEntity<DepartmentResponse> getDepartmentList(String depCode, String depName, String facultyName) {
        try{
            DepartmentResponse departmentResponse = new DepartmentResponse();
            departmentResponse.setDepartmentList(departmentRepository.getDepartmentList(depCode,depName,facultyName));
            return new ResponseEntity<>(departmentResponse,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> insertDepartment(DepartmentRequest departmentRequest) {
        try{
            Department department = new Department();
            Integer n = departmentRepository.countDepByFaculty(departmentRequest.getFacultyCode());
            department.setDepCode(departmentRequest.getFacultyCode()+ (n+1));
            department.setDepName(departmentRequest.getDepName());
            department.setStatusInfo(statusRepository.findByStatusCode("AC"));
            department.setFacultyId(facultyRepository.findByFacultyCode(departmentRequest.getFacultyCode()));
            department.setCreateDate(new Date());
            department.setCreateBy(staffRepository.findById(departmentRequest.getCreateBy()).orElseThrow(()-> new EntityNotFoundException("Staff not found")));
            departmentRepository.save(department);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully insert Department");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed insert Department");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> updateDepartment(DepartmentRequest departmentRequest) {
        try{
            Department department = departmentRepository.findById(departmentRequest.getDepId()).orElseThrow(()-> new EntityNotFoundException("department not found"));
            if(departmentRequest.getFacultyCode() != null && !departmentRequest.getFacultyCode().equals(department.getFacultyId().getFacultyCode())){
                department.setStatusInfo(statusRepository.findByStatusCode("IN"));
                department.setUpdateDate(new Date());
                department.setUpdateBy(staffRepository.findById(departmentRequest.getUpdateBy()).orElseThrow(()-> new EntityNotFoundException("Staff not found")));
                departmentRepository.save(department);

                Department newDepartment = new Department();
                Integer n = departmentRepository.countDepByFaculty(departmentRequest.getFacultyCode());
                newDepartment.setDepCode(departmentRequest.getFacultyCode()+ (n+1));
                newDepartment.setDepName(departmentRequest.getDepName());
                newDepartment.setStatusInfo(statusRepository.findByStatusCode("AC"));
                newDepartment.setFacultyId(facultyRepository.findByFacultyCode(departmentRequest.getFacultyCode()));
                newDepartment.setCreateDate(new Date());
                newDepartment.setCreateBy(staffRepository.findById(departmentRequest.getUpdateBy()).orElseThrow(()-> new EntityNotFoundException("Staff not found")));
                departmentRepository.save(newDepartment);
            } else{
                if(departmentRequest.getDepName()!=null)
                    department.setDepName(departmentRequest.getDepName());
                department.setUpdateDate(new Date());
                department.setUpdateBy(staffRepository.findById(departmentRequest.getUpdateBy()).orElseThrow(()-> new EntityNotFoundException("Staff not found")));
                departmentRepository.save(department);
            }
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully insert Department");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed insert Department");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> deleteDepartment(DepartmentRequest departmentRequest) {
        try{
            Department department = departmentRepository.findById(departmentRequest.getDepId()).orElseThrow(()-> new EntityNotFoundException("department not Found"));
            department.setStatusInfo(statusRepository.findByStatusCode("IN"));
            department.setUpdateDate(new Date());
            department.setUpdateBy(staffRepository.findById(departmentRequest.getUpdateBy()).orElseThrow(()-> new EntityNotFoundException("Staff not found")));
            departmentRepository.save(department);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully delete Department");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed delete Department");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }
}
