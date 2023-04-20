package demo.backend.ManageInternship.model.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class DepartmentData {
    private Integer depId;
    private String depCode;
    private String depName;
    private String facultyName;

    private String statusInfo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createDate;
    private String createBy;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updateDate;
    private String updateBy;

    public DepartmentData(){

    }

    public DepartmentData(Integer depId, String depCode, String depName
            , String facultyName, String statusInfo, Date createDate, String createBy, Date updateDate, String updateBy) {
        this.depId = depId;
        this.depCode = depCode;
        this.depName = depName;
        this.facultyName = facultyName;
        this.statusInfo = statusInfo;
        this.createDate = createDate;
        this.createBy = createBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
    }
}
