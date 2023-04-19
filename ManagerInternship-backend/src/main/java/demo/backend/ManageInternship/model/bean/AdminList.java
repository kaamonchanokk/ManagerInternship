package demo.backend.ManageInternship.model.bean;

import lombok.Data;

@Data
public class AdminList {
    private Integer userId;
    private String userCode;
    private String userName;
    private String statusInfo;

    public AdminList() {

    }
    public AdminList(Integer userId, String userCode, String userName, String statusInfo) {
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.statusInfo = statusInfo;
    }
}
