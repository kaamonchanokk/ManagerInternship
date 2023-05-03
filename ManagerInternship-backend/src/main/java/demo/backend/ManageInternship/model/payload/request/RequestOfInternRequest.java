package demo.backend.ManageInternship.model.payload.request;

import lombok.Data;

import java.util.Date;

@Data
public class RequestOfInternRequest {
    private Integer requestId;
    //--student--//
    private Date dateFrom;
    private Date dateTo;
    private String studentCode;
    private Integer companyId;
    private String createBy;
    private String updateBy;

    //--teacher---//
    private String statusApprove;
    private String approveBy;


}
