package demo.backend.ManageInternship.repository;

import demo.backend.ManageInternship.model.bean.ReportRequestData;
import demo.backend.ManageInternship.model.bean.RequestData;
import demo.backend.ManageInternship.model.entity.Request;
import demo.backend.ManageInternship.model.payload.response.report.ReportRequestResponse;
import demo.backend.ManageInternship.model.payload.response.report.ReportYearResponse;
import demo.backend.ManageInternship.repository.CustomRepository.CustomRequestRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request,Integer>, CustomRequestRepository {
    @Query(nativeQuery = true)
//    List<RequestData> getRequestList();
    List<RequestData> getRequestList(
            @Param(value = "dateRequestFrom") Date dateRequestFrom
            ,@Param(value = "dateRequestTo") Date dateRequestTo
            ,@Param(value = "studentCode") String studentCode
            ,@Param(value = "studentName") String studentName
            ,@Param(value = "companyName") String companyName
            ,@Param(value = "statusApprove") String statusApprove
            ,@Param(value = "approveBy") String approveBy
    );

    @Query("select s.statusCode from Request r inner join Status s on s.statusId = r.statusApprove where r.requestId = :requestId")
    String checkStatusId(@Param(value = "requestId") Integer requestId);

    @Query(nativeQuery = true)
    ReportRequestData getReportRequest();

    @Query(nativeQuery = true)
    List<ReportYearResponse> getReportYear(@Param(value = "year") String year);
}
