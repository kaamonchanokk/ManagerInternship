package demo.backend.ManageInternship.model.payload.response.masterData;

import demo.backend.ManageInternship.model.bean.DepartmentData;
import lombok.Data;

import java.util.List;


@Data
public class DepartmentResponse {
  public List<DepartmentData> departmentList;
}
