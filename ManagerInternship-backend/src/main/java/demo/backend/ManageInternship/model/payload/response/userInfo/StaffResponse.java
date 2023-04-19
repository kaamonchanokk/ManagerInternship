package demo.backend.ManageInternship.model.payload.response.userInfo;

import demo.backend.ManageInternship.model.bean.StaffList;
import lombok.Data;

import java.util.List;

@Data
public class StaffResponse {
    private List<StaffList> StaffList;
}

