package demo.backend.ManageInternship.model.payload.response;

import demo.backend.ManageInternship.model.bean.RequestData;
import lombok.Data;

import java.util.List;

@Data
public class RequestOfInternResponse {
    public List<RequestData> requestList;
}
