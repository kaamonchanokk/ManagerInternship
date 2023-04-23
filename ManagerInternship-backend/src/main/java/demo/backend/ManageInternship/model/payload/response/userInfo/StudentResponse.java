package demo.backend.ManageInternship.model.payload.response.userInfo;

import demo.backend.ManageInternship.model.bean.StudentData;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class StudentResponse {
   private List<StudentData> studentList;
}
