package demo.backend.ManageInternship.service.masterData;

import demo.backend.ManageInternship.model.payload.request.masterData.PositionRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.masterData.PositionResponse;
import org.springframework.http.ResponseEntity;

public interface PositionService {
    ResponseEntity<PositionResponse> getPositionList(String positionCode, String positionName);

    ResponseEntity<MessageResponse> insertPosition(PositionRequest positionRequest);

    ResponseEntity<MessageResponse> updatePosition(PositionRequest positionRequest);

    ResponseEntity<MessageResponse> deletePosition(PositionRequest positionRequest);
}
