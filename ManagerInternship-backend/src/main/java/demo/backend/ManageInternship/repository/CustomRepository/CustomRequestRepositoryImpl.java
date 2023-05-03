package demo.backend.ManageInternship.repository.CustomRepository;

import demo.backend.ManageInternship.model.payload.request.RequestOfInternRequest;
import org.intellij.lang.annotations.Language;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class CustomRequestRepositoryImpl implements CustomRequestRepository {
    @PersistenceContext
    EntityManager em;
    @Override
    @Transactional
    @Modifying
    public void insertRequest(RequestOfInternRequest requestOfInternRequest) {
        @Language("TSQL") String sql =
                "INSERT INTO request VALUES " +
                        "(" +
                        "null" +
                        ",:dateFrom" +
                        ",:dateTo" +
                        ",(SELECT STUDENT_ID FROM student WHERE STUDENT_CODE = :studentCode) " +
                        ",:companyId" +
                        ",(SELECT STATUS_ID FROM status WHERE STATUS_CODE = 'PD' AND STATUS_TYPE = 'STATUS_APPROVE')" +
                        ",null" +
                        ",null" +
                        ",CURRENT_TIMESTAMP" +
                        ",:createBy" +
                        ",null" +
                        ",null" +
                        ",(SELECT STATUS_ID FROM status WHERE STATUS_CODE = 'AC' AND STATUS_TYPE = 'STATUS_INFO')" +
                        ",(SELECT SCHEDULE_ID FROM schedule s WHERE CURRENT_TIMESTAMP BETWEEN s.SCHEDULE_FROM AND s.SCHEDULE_TO)" +
                        ")";
        Query query = em.createNativeQuery(sql);
        query.setParameter("dateFrom", requestOfInternRequest.getDateFrom());
        query.setParameter("dateTo", requestOfInternRequest.getDateTo());
        query.setParameter("studentCode", requestOfInternRequest.getStudentCode());
        query.setParameter("companyId", requestOfInternRequest.getCompanyId());
        query.setParameter("createBy", requestOfInternRequest.getCreateBy());
        query.executeUpdate();
    }

    @Override
    @Transactional
    @Modifying
    public void updateRequest(RequestOfInternRequest requestOfInternRequest) {
        @Language("TSQL") String sql =
                "UPDATE request SET " +
                        "DATE_FROM = :dateFrom" +
                        ",DATE_TO = :dateto" +
                        ",COMPANY_ID = :companyId " +
                        ",STATUS_APPROVE = (SELECT STATUS_ID FROM status WHERE STATUS_CODE = 'PD' AND STATUS_TYPE = 'STATUS_APPROVE')" +
                        ",APPROVE_DATE = NULL" +
                        ",APPROVE_BY = NULL" +
                        ",UPDATE_DATE = CURRENT_TIMESTAMP " +
                        ",UPDATE_BY = :updateBy " +
                        "WHERE REQUEST_ID = :requestId ";
        Query query = em.createNativeQuery(sql);
        query.setParameter("dateFrom", requestOfInternRequest.getDateFrom());
        query.setParameter("dateto", requestOfInternRequest.getDateTo());
        query.setParameter("companyId", requestOfInternRequest.getCompanyId());
        query.setParameter("updateBy", requestOfInternRequest.getUpdateBy());
        query.setParameter("requestId", requestOfInternRequest.getRequestId());
        query.executeUpdate();
    }

    @Override
    @Transactional
    @Modifying
    public void deleteRequest(RequestOfInternRequest requestOfInternRequest) {
        @Language("TSQL") String sql =
                "UPDATE request SET " +
                        "STATUS_INFO = (SELECT STATUS_ID FROM status s WHERE s.STATUS_CODE = 'IN' AND s.STATUS_TYPE = 'STATUS_INFO' )" +
                        ",UPDATE_DATE = CURRENT_TIMESTAMP " +
                        ",UPDATE_BY = :updateBy " +
                        "WHERE REQUEST_ID = :requestId ";
        Query query = em.createNativeQuery(sql);
        query.setParameter("updateBy", requestOfInternRequest.getUpdateBy());
        query.setParameter("requestId", requestOfInternRequest.getRequestId());
        query.executeUpdate();
    }

    @Override
    @Transactional
    @Modifying
    public void updateStatusRequest(RequestOfInternRequest requestOfInternRequest) {
        @Language("TSQL") String sql =
                "UPDATE request SET STATUS_APPROVE = (SELECT STATUS_ID FROM status s WHERE s.STATUS_CODE = :statusApprove AND s.STATUS_TYPE = 'STATUS_APPROVE' )" +
                        ",APPROVE_DATE = CURRENT_TIMESTAMP " +
                        ",APPROVE_BY = :approveBy " +
                        "WHERE REQUEST_ID = :requestId ";
        Query query = em.createNativeQuery(sql);
        query.setParameter("statusApprove", requestOfInternRequest.getStatusApprove());
        query.setParameter("approveBy", requestOfInternRequest.getApproveBy());
        query.setParameter("requestId", requestOfInternRequest.getRequestId());
        query.executeUpdate();
    }
}
