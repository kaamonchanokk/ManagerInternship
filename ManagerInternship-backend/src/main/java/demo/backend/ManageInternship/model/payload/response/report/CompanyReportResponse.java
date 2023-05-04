package demo.backend.ManageInternship.model.payload.response.report;

import lombok.Data;

@Data
public class CompanyReportResponse {
    private String companyName;
    private Integer totalInternship;

    public CompanyReportResponse(String companyName, Integer totalInternship) {
        this.companyName = companyName;
        this.totalInternship = totalInternship;
    }

    public CompanyReportResponse() {
    }
}
