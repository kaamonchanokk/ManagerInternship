package demo.backend.ManageInternship.model.payload.request.userInfo;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherRequest {
    private Integer teacherId;
    private String teacherCode;
    private String teacherTitle;
    private String teacherName;
    private String teacherLastName;
    private String departmentCode;
    private Integer createBy;
    private Integer updateBy;
}
