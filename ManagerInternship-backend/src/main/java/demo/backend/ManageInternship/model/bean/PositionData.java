package demo.backend.ManageInternship.model.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
public class PositionData {
    private Integer positionId;
    private String positionCode;
    private String positionName;
    private String statusInfo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date createDate;
    private String createBy;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date updateDate;
    private String updateBy;

    public PositionData(Integer positionId, String positionCode, String positionName, String statusInfo, Date createDate, String createBy, Date updateDate, String updateBy) {
        this.positionId = positionId;
        this.positionCode = positionCode;
        this.positionName = positionName;
        this.statusInfo = statusInfo;
        this.createDate = createDate;
        this.createBy = createBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
    }
    public PositionData() {

    }
}
