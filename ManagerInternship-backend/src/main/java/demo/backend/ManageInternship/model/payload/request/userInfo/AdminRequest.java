package demo.backend.ManageInternship.model.payload.request.userInfo;

import lombok.Data;

@Data
public class AdminRequest {
    private Integer userId;
    private String userCode;
    private String userName;
    private String userLastName;
    private String statusInfo;
}
