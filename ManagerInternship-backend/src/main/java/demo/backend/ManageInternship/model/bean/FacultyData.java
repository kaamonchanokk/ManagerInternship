package demo.backend.ManageInternship.model.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class FacultyData {
    private  Integer facultyId;
    private  String facultyCode;
    private  String facultyName;
    private  String statusInfo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createDate;
    private  String createBy;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private  Date updateDate;
    private  String updateBy;

    public FacultyData(){

    }
    public FacultyData(Integer facultyId, String facultyCode, String facultyName, String statusInfo, Date createDate, String createBy, Date updateDate, String updateBy) {
        this.facultyId = facultyId;
        this.facultyCode = facultyCode;
        this.facultyName = facultyName;
        this.statusInfo = statusInfo;
        this.createDate = createDate;
        this.createBy = createBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
    }
}
