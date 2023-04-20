package demo.backend.ManageInternship.model.payload.request.masterData;

import lombok.Data;

@Data
public class CompanyRequest {
    private Integer companyId;
    private String companyName;
    private String companyTelephone;
    private String companyAddress;
    private Integer createBy;
    private Integer updateBy;
}
