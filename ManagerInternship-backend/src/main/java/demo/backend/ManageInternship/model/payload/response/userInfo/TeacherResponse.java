package demo.backend.ManageInternship.model.payload.response.userInfo;

import demo.backend.ManageInternship.model.bean.TeacherData;
import lombok.Data;

import java.util.List;

@Data
public class TeacherResponse {
    private List<TeacherData> teacherList;
}
