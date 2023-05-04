package demo.backend.ManageInternship.model.payload.response.report;

import lombok.Data;

@Data
public class ReportFacultyResponse {
    private String facultyName;
    private Integer totalIntern;

    public ReportFacultyResponse(String facultyName, Integer totalIntern) {
        this.facultyName = facultyName;
        this.totalIntern = totalIntern;
    }

    public ReportFacultyResponse() {
    }
}
