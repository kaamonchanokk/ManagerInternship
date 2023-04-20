package demo.backend.ManageInternship.model.payload.response.masterData;

import demo.backend.ManageInternship.model.bean.FacultyData;
import lombok.Data;

import java.util.List;

@Data
public class FacultyResponse {
    private List<FacultyData> facultyList;
}
