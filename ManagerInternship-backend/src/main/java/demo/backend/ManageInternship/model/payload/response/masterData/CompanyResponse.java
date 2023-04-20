package demo.backend.ManageInternship.model.payload.response.masterData;

import demo.backend.ManageInternship.model.bean.CompanyData;
import lombok.Data;

import java.util.List;

@Data
public class CompanyResponse {
    private List<CompanyData> companyList;
}
