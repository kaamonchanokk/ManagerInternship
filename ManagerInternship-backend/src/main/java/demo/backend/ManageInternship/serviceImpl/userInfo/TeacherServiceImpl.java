package demo.backend.ManageInternship.serviceImpl.userInfo;

import demo.backend.ManageInternship.model.entity.Department;
import demo.backend.ManageInternship.model.entity.Teacher;
import demo.backend.ManageInternship.model.payload.request.userInfo.TeacherRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.userInfo.TeacherResponse;
import demo.backend.ManageInternship.repository.DepartmentRepository;
import demo.backend.ManageInternship.repository.StaffRepository;
import demo.backend.ManageInternship.repository.StatusRepository;
import demo.backend.ManageInternship.repository.TeacherRepository;
import demo.backend.ManageInternship.service.userInfo.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    StatusRepository statusRepository;

    MessageResponse messageResponse = new MessageResponse();
    @Override
    public ResponseEntity<TeacherResponse> getTeacherList(String teacherCode, String teacherName, String teacherLastName) {
        try{
            TeacherResponse teacherResponse = new TeacherResponse();
            teacherResponse.setTeacherList(teacherRepository.getTeacherList(teacherCode,teacherName,teacherLastName));
            return new ResponseEntity<>(teacherResponse,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> insertTeacher(TeacherRequest teacherRequest) {
        try{
            Teacher teacher =  new Teacher();
            Integer n = teacherRepository.countDepartment(teacherRequest.getDepartmentCode());
            teacher.setTeacherCode(teacherRequest.getDepartmentCode() + String.format("%0" + 3 + "d", n+1));
            teacher.setTeacherTitle(teacherRequest.getTeacherTitle());
            teacher.setTeacherName(teacherRequest.getTeacherName());
            teacher.setTeacherLastname(teacherRequest.getTeacherLastName());
            teacher.setStatusTeacher(statusRepository.findByStatusTeacherCode("W"));
            teacher.setStatusInfo(statusRepository.findByStatusCode("AC"));
            teacher.setDepId(departmentRepository.getDepartmentByCode(teacherRequest.getDepartmentCode()));
            teacher.setCreateDate(new Date());
            teacher.setCreateBy(staffRepository.findById(teacherRequest.getCreateBy()).orElseThrow(()-> new EntityNotFoundException("user not found")));
            teacherRepository.save(teacher);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully insert teacher");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed insert teacher");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> updateTeacher(TeacherRequest teacherRequest) {
        try{
            Teacher teacher = teacherRepository.findById(teacherRequest.getTeacherId()).orElseThrow(()->new EntityNotFoundException());
            if(!teacher.getDepId().getDepCode().equals(teacherRequest.getDepartmentCode()) && teacherRequest.getDepartmentCode() != null){
                teacher.setStatusInfo(statusRepository.findByStatusCode("IN"));
                teacher.setStatusTeacher(statusRepository.findByStatusTeacherCode("NW"));
                teacher.setUpdateDate(new Date());
                teacher.setUpdateBy(staffRepository.findById(teacherRequest.getUpdateBy()).orElseThrow(()->new EntityNotFoundException()));
                teacherRepository.save(teacher);
                Teacher newTeacher = new Teacher();
                Integer n = teacherRepository.countDepartment(teacherRequest.getDepartmentCode());
                newTeacher.setTeacherCode(teacherRequest.getDepartmentCode() + String.format("%0" + 3 + "d", n+1));
                newTeacher.setTeacherTitle(teacherRequest.getTeacherTitle());
                newTeacher.setTeacherName(teacherRequest.getTeacherName());
                newTeacher.setTeacherLastname(teacherRequest.getTeacherLastName());
                newTeacher.setStatusTeacher(statusRepository.findByStatusTeacherCode("W"));
                newTeacher.setStatusInfo(statusRepository.findByStatusCode("AC"));
                newTeacher.setDepId(departmentRepository.getDepartmentByCode(teacherRequest.getDepartmentCode()));
                newTeacher.setCreateDate(new Date());
                newTeacher.setCreateBy(staffRepository.findById(teacherRequest.getUpdateBy()).orElseThrow(()-> new EntityNotFoundException("user not found")));
                teacherRepository.save(newTeacher);
            }
            else{
                if(teacherRequest.getTeacherTitle()!=null)
                    teacher.setTeacherTitle(teacherRequest.getTeacherTitle());
                if(teacherRequest.getTeacherName()!=null)
                    teacher.setTeacherName(teacherRequest.getTeacherName());
                if(teacherRequest.getTeacherLastName()!=null)
                    teacher.setTeacherLastname(teacherRequest.getTeacherLastName());
                teacher.setUpdateDate(new Date());
                teacher.setUpdateBy(staffRepository.findById(teacherRequest.getUpdateBy()).orElseThrow(()->new EntityNotFoundException()));
                teacherRepository.save(teacher);
            }
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully update teacher");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed update teacher");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> deleteTeacher(TeacherRequest teacherRequest) {
        try{
            Teacher teacher = teacherRepository.findById(teacherRequest.getTeacherId()).orElseThrow(()->new EntityNotFoundException());
            teacher.setStatusInfo(statusRepository.findByStatusCode("IN"));
            teacher.setUpdateDate(new Date());
            teacher.setUpdateBy(staffRepository.findById(teacherRequest.getUpdateBy()).orElseThrow(()->new EntityNotFoundException()));
            teacherRepository.save(teacher);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully delete teacher");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        }catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed delete teacher");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }
}
