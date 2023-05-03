package demo.backend.ManageInternship.model.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RequestData {

    private Integer requestId;
    private String requestYear;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date requestDate;
    private String dateInternship;
    private String studentCode;
    private String studentName;
    private String companyName;
    private String statusApprove;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date approveDate;
    private String approveBy;
    private String statusInfo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createDate;
    private String createBy;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updateDate;
    private String updateBy;

    public RequestData(Integer requestId, String requestYear, Date requestDate, String dateInternship, String studentCode, String studentName, String companyName, String statusApprove, Date approveDate, String approveBy, String statusInfo, Date createDate, String createBy, Date updateDate, String updateBy) {
        this.requestId = requestId;
        this.requestYear = requestYear;
        this.requestDate = requestDate;
        this.dateInternship = dateInternship;
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.companyName = companyName;
        this.statusApprove = statusApprove;
        this.approveDate = approveDate;
        this.approveBy = approveBy;
        this.statusInfo = statusInfo;
        this.createDate = createDate;
        this.createBy = createBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
    }

    public RequestData() {

    }
}
