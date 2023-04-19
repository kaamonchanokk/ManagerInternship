package demo.backend.ManageInternship.model.payload.request.userInfo;

import lombok.Data;

@Data
public class StaffRequest {
    private Integer staffId;
    private String staffCode;
    private String staffTitle;
    private String staffName;
    private String staffLastName;
    private String staffPosition;
    private String statusInfo;
}
