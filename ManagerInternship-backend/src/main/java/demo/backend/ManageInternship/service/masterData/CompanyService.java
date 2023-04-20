package demo.backend.ManageInternship.service.masterData;

import demo.backend.ManageInternship.model.payload.request.masterData.CompanyRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.masterData.CompanyResponse;
import org.springframework.http.ResponseEntity;

public interface CompanyService {
    ResponseEntity<CompanyResponse> getCompanyList(String companyName);

    ResponseEntity<MessageResponse> insertCompany(CompanyRequest companyRequest);

    ResponseEntity<MessageResponse> updateCompany(CompanyRequest companyRequest);

    ResponseEntity<MessageResponse> deleteCompany(CompanyRequest companyRequest);
}
