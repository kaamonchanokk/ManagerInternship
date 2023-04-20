package demo.backend.ManageInternship.model.payload.request.masterData;

import lombok.Data;

import java.util.Date;

@Data
public class PositionRequest {
    private Integer positionId;
    private String positionCode;
    private String positionName;
    private Integer createBy;
    private Integer updateBy;
}
