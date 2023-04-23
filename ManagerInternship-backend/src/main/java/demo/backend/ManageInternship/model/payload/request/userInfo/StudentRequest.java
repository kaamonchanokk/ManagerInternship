package demo.backend.ManageInternship.model.payload.request.userInfo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class StudentRequest {
    private Integer studentId;
    private String studentCode;
    private String studentTitle;
    private String studentName;
    private String studentLastName;
    private String departmentCode;
    private BigDecimal studentGrade;
    private String advisorCode;
    private Integer studentYear;
    private Date createdDate;
    private Integer createBy;
    private Date updateDate;
    private Integer updateBy;
}
