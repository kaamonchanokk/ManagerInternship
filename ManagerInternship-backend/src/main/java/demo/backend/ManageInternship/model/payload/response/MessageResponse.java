package demo.backend.ManageInternship.model.payload.response;

import lombok.Data;

@Data
public class MessageResponse {
    private String status;
    private String message;

    public MessageResponse() {
    }
    public MessageResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
