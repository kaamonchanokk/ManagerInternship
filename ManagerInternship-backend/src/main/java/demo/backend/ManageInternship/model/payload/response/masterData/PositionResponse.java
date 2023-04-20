package demo.backend.ManageInternship.model.payload.response.masterData;

import demo.backend.ManageInternship.model.bean.PositionData;
import lombok.Data;

import java.util.List;
@Data
public class PositionResponse {
    private List<PositionData> positionList;
}
