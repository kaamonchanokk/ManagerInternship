package demo.backend.ManageInternship.serviceImpl.masterData;

import demo.backend.ManageInternship.model.entity.Company;
import demo.backend.ManageInternship.model.payload.request.masterData.CompanyRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.masterData.CompanyResponse;
import demo.backend.ManageInternship.repository.CompanyRepository;
import demo.backend.ManageInternship.repository.StaffRepository;
import demo.backend.ManageInternship.repository.StatusRepository;
import demo.backend.ManageInternship.service.masterData.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    StatusRepository statusRepository;

    MessageResponse messageResponse = new MessageResponse();
    @Override
    public ResponseEntity<CompanyResponse> getCompanyList(String companyName) {
        try{
            CompanyResponse companyResponse = new CompanyResponse();
            companyResponse.setCompanyList(companyRepository.getCompanyList(companyName));
            return new ResponseEntity<>(companyResponse,HttpStatus.OK);

        } catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> insertCompany(CompanyRequest companyRequest) {
        try{
            Company company = new Company();
            company.setCompanyName(companyRequest.getCompanyName());
            company.setCompanyAddress(companyRequest.getCompanyAddress());
            company.setCompanyTelephone(companyRequest.getCompanyTelephone());
            company.setCreateDate(new Date());
            company.setCreateBy(staffRepository.findById(companyRequest.getCreateBy()).orElseThrow(() -> new EntityNotFoundException("User not found")));
            company.setStatusInfo(statusRepository.findByStatusCode("AC"));
            companyRepository.save(company);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully Insert Company");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        } catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed Insert Company");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> updateCompany(CompanyRequest companyRequest) {
        try{
            Company company = companyRepository.findById(companyRequest.getCompanyId()).orElseThrow(() -> new EntityNotFoundException("Company not found"));
            if(companyRequest.getCompanyName() != null)
                company.setCompanyName(companyRequest.getCompanyName());
            if(companyRequest.getCompanyAddress() != null)
                company.setCompanyAddress(companyRequest.getCompanyAddress());
            if(companyRequest.getCompanyTelephone() != null)
                company.setCompanyTelephone(companyRequest.getCompanyTelephone());
            company.setUpdateDate(new Date());
            company.setUpdateBy(staffRepository.findById(companyRequest.getUpdateBy()).orElseThrow(() -> new EntityNotFoundException("User not found")));
            companyRepository.save(company);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully Update Company");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        } catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed Update Company");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<MessageResponse> deleteCompany(CompanyRequest companyRequest) {
        try{
            Company company = companyRepository.findById(companyRequest.getCompanyId()).orElseThrow(() -> new EntityNotFoundException("Company not found"));
            company.setStatusInfo(statusRepository.findByStatusCode("IN"));
            company.setUpdateDate(new Date());
            company.setUpdateBy(staffRepository.findById(companyRequest.getUpdateBy()).orElseThrow(() -> new EntityNotFoundException("User not found")));
            companyRepository.save(company);
            messageResponse.setStatus("Success");
            messageResponse.setMessage("Successfully delete Company");
            return new ResponseEntity<>(messageResponse,HttpStatus.OK);
        } catch (Exception e){
            messageResponse.setStatus("Failed");
            messageResponse.setMessage("Failed delete Company");
            return new ResponseEntity<>(messageResponse,HttpStatus.BAD_REQUEST);
        }
    }
}
