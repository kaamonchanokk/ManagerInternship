package demo.backend.ManageInternship.model.payload.request.masterData;

import lombok.Data;

@Data
public class DepartmentRequest {
    private Integer depId;
    private String depCode;
    private String depName;
    private String facultyCode;
    private Integer createBy;
    private Integer updateBy;
}
