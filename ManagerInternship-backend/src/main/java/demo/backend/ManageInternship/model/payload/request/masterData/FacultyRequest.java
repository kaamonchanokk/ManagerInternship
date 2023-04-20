package demo.backend.ManageInternship.model.payload.request.masterData;

import lombok.Data;

@Data
public class FacultyRequest {
    private Integer facultyId;
    private String facultyCode;
    private String facultyName;
    private Integer createBy;
    private Integer updateBy;
}
