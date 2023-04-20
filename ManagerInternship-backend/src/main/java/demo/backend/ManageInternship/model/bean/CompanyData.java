package demo.backend.ManageInternship.model.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CompanyData {

    private Integer companyId;
    private String companyName;
    private String companyTelephone;
    private String companyAddress;
    private String statusInfo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createDate;
    private String createBy;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updateDate;
    private String updateBy;

    public CompanyData() {
    }

    public CompanyData(Integer companyId, String companyName, String companyTelephone, String companyAddress, String statusInfo, Date createDate, String createBy, Date updateDate, String updateBy) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyTelephone = companyTelephone;
        this.companyAddress = companyAddress;
        this.statusInfo = statusInfo;
        this.createDate = createDate;
        this.createBy = createBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
    }
}
