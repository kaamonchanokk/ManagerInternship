package demo.backend.ManageInternship.model.bean;

import lombok.Data;

@Data
public class StaffList {
    private Integer staffId;
    private String staffCode;
    private String staffName;
    private String staffPosition;
    private String statusInfo;

    public StaffList() {

    }
    public StaffList(Integer staffId, String staffCode, String staffName,String staffPosition, String statusInfo) {
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.staffName = staffName;
        this.staffPosition = staffPosition;
        this.statusInfo = statusInfo;
    }
}
