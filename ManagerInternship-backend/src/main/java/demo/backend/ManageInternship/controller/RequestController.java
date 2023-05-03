package demo.backend.ManageInternship.controller;

import demo.backend.ManageInternship.model.payload.request.RequestOfInternRequest;
import demo.backend.ManageInternship.model.payload.response.MessageResponse;
import demo.backend.ManageInternship.model.payload.response.RequestOfInternResponse;
import demo.backend.ManageInternship.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/request")
public class RequestController {
    @Autowired
    RequestService requestService;

    @GetMapping("/requestList")
    public ResponseEntity<RequestOfInternResponse> getRequestList(
            @RequestParam(value = "dateRequestFrom",required = false) Date dateRequestFrom,
            @RequestParam(value = "dateRequestTo",required = false) Date dateRequestTo,
            @RequestParam(value = "studentCode",required = false) String  studentCode,
            @RequestParam(value = "studentName",required = false) String  studentName,
            @RequestParam(value = "companyName",required = false) String  companyName,
            @RequestParam(value = "statusApprove",required = false) String  statusApprove,
            @RequestParam(value = "approveBy",required = false) String  approveBy
            )

    {
        return requestService.getRequestList(dateRequestFrom,dateRequestTo,studentCode,studentName,companyName,statusApprove,approveBy);
    }
    @PostMapping("/insertRequest")
    public ResponseEntity<MessageResponse> insertRequest(@RequestBody RequestOfInternRequest requestOfInternRequest){
        return requestService.insertRequest(requestOfInternRequest);
    }

    @PutMapping("/updateRequest")
    public ResponseEntity<MessageResponse> updateRequest(@RequestBody RequestOfInternRequest requestOfInternRequest){
        return requestService.updateRequest(requestOfInternRequest);
    }

    @PatchMapping("/deleteRequest")
    public ResponseEntity<MessageResponse> deleteRequest(@RequestBody RequestOfInternRequest requestOfInternRequest){
        return requestService.deleteRequest(requestOfInternRequest);
    }

    //--forteacher---//
    @PutMapping("updateStatusRequest")
    public ResponseEntity<MessageResponse> updateStatusRequest(@RequestBody RequestOfInternRequest requestOfInternRequest){
        return requestService.updateStatusRequest(requestOfInternRequest);
    }
}
