package demo.backend.ManageInternship.model.bean;

import lombok.Data;

@Data
public class ReportRequestData {
    private Integer approve;
    private Integer disApprove;
    private Integer pending;

    public ReportRequestData(Integer approve, Integer disApprove, Integer pending) {
        this.approve = approve;
        this.disApprove = disApprove;
        this.pending = pending;
    }
    public ReportRequestData() {
    }
}
