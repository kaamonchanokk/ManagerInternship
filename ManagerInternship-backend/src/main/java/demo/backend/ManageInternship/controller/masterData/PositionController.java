package demo.backend.ManageInternship.controller.masterData;

import demo.backend.ManageInternship.model.payload.request.masterData.PositionRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.masterData.PositionResponse;
import demo.backend.ManageInternship.service.masterData.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/masterData")
public class PositionController {

    @Autowired
    PositionService positionService;

    @GetMapping("/positionList")
    public ResponseEntity<PositionResponse> getPositionList(
            @RequestParam(value = "positionCode",required = false) String positionCode,
            @RequestParam(value = "positionName",required = false) String positionName
    ) {
        return positionService.getPositionList(positionCode,positionName);
    }
    @PostMapping("/insertPosition")
    public ResponseEntity<MessageResponse> insertPosition(
            @RequestBody PositionRequest positionRequest)
    {
        return positionService.insertPosition(positionRequest);
    }
    @PutMapping("/updatePosition")
    public ResponseEntity<MessageResponse> upddatePosition(
            @RequestBody PositionRequest positionRequest)
    {
        return positionService.updatePosition(positionRequest);
    }
    @PatchMapping("/deletePosition")
    public ResponseEntity<MessageResponse> deletePosition(
            @RequestBody PositionRequest positionRequest
    )
    {
        return positionService.deletePosition(positionRequest);
    }
}
