package demo.backend.ManageInternship.controller.masterData;

import demo.backend.ManageInternship.model.payload.request.masterData.CompanyRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.masterData.CompanyResponse;
import demo.backend.ManageInternship.service.masterData.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/masterData")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/companyList")
    public ResponseEntity<CompanyResponse> getCompanyList(
            @RequestParam(value = "companyName",required = false) String companyName
    ){
        return companyService.getCompanyList(companyName);
    }

    @PostMapping("/insertCompany")
    public ResponseEntity<MessageResponse> insertCompany(@RequestBody CompanyRequest companyRequest){
        return companyService.insertCompany(companyRequest);
    }

    @PutMapping("/updateCompany")
    public ResponseEntity<MessageResponse> updateCompany(@RequestBody CompanyRequest companyRequest){
        return companyService.updateCompany(companyRequest);
    }

    @PatchMapping("/deleteCompany")
    public ResponseEntity<MessageResponse> deleteCompany(@RequestBody CompanyRequest companyRequest){
        return companyService.deleteCompany(companyRequest);
    }
}
