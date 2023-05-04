package demo.backend.ManageInternship.model.payload.response.report;

import lombok.Data;

@Data
public class ReportYearResponse {
    private String scheduleYear;
    private Integer totalIntern;

    public ReportYearResponse(String scheduleYear, Integer totalIntern) {
        this.scheduleYear = scheduleYear;
        this.totalIntern = totalIntern;
    }

    public ReportYearResponse() {
    }
}
