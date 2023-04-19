package demo.backend.ManageInternship.model.payload.response.userInfo;

import demo.backend.ManageInternship.model.bean.AdminList;
import lombok.Data;

import java.util.List;

@Data
public class AdminResponse {
    private List<AdminList> AdminList;
}

