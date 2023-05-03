/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo.backend.ManageInternship.model.entity;

import demo.backend.ManageInternship.model.bean.PositionData;
import demo.backend.ManageInternship.model.bean.RequestData;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Kamonchanok
 */
@Data
@Entity
@Table(name = "request")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Request.getRequestList",
                query = "SELECT r.REQUEST_ID" +
                        ",CONCAT(s.SCHEDULE_YEAR,'-','0',s.SCHEDULE_SEMESTER) AS REQUEST_YEAR " +
                        ",r.CREATE_DATE AS REQUEST_DATE" +
                        ",CONCAT(CONCAT(DAY(r.DATE_FROM), ' ', SUBSTRING(MONTHNAME(r.DATE_FROM), 1, 3), ' ', YEAR(r.DATE_FROM))" +
                        ",' - '" +
                        ",CONCAT(DAY(r.DATE_TO), ' ', SUBSTRING(MONTHNAME(r.DATE_TO), 1, 3), ' ', YEAR(r.DATE_TO))) AS DATE_INTERNSHIP" +
                        ",st.STUDENT_CODE " +
                        ",CONCAT(st.STUDENT_TITLE,st.STUDENT_NAME,' ',st.STUDENT_LASTNAME) AS STUDENT_NAME  " +
                        ",c.COMPANY_NAME " +
                        ",sta1.STATUS_NAME AS STATUS_APPROVE " +
                        ",r.APPROVE_DATE" +
                        ",CONCAT(t.TEACHER_TITLE,t.TEACHER_NAME,' ',t.TEACHER_LASTNAME) AS APPROVE_BY  " +
                        ",sta2.STATUS_NAME AS STATUS_INFO " +
                        ",r.CREATE_DATE " +
                        ",CONCAT(st2.STUDENT_TITLE,st2.STUDENT_NAME,' ',st2.STUDENT_LASTNAME) AS CREATE_BY  " +
                        ",r.UPDATE_DATE " +
                        ",CONCAT(st3.STUDENT_TITLE,st3.STUDENT_NAME,' ',st3.STUDENT_LASTNAME) AS UPDATE_BY  " +
                        " FROM request r " +
                        "INNER JOIN schedule s on r.SCHEDULE_ID = s.SCHEDULE_ID " +
                        "INNER JOIN student st on r.STUDENT_ID = st.STUDENT_ID " +
                        "INNER JOIN company c on r.COMPANY_ID = c.COMPANY_ID " +
                        "LEFT JOIN status sta1 on r.STATUS_APPROVE = sta1.STATUS_ID " +
                        "INNER JOIN status sta2 on r.STATUS_INFO = sta2.STATUS_ID " +
                        "INNER JOIN student st2 on r.STUDENT_ID = st2.STUDENT_ID " +
                        "LEFT JOIN student st3 on r.STUDENT_ID = st3.STUDENT_ID " +
                        "LEFT JOIN teacher t on r.APPROVE_BY = t.TEACHER_ID " +
                        "WHERE  (st.STUDENT_CODE = :studentCode OR :studentCode IS NULL) " +
                        "AND  (st.STUDENT_NAME LIKE CONCAT('%', :studentName, '%') OR st.STUDENT_LASTNAME LIKE CONCAT('%', :studentName, '%') OR :studentName IS NULL ) " +
                        "AND  (t.TEACHER_NAME LIKE CONCAT('%', :approveBy, '%') OR t.TEACHER_LASTNAME LIKE CONCAT('%', :approveBy, '%') OR :approveBy IS NULL ) " +
                        "AND (c.COMPANY_NAME = :companyName OR :companyName IS NULL) " +
                        "AND (" +
                        "(r.CREATE_DATE between :dateRequestFrom AND :dateRequestTo AND (:dateRequestFrom IS NOT NULL AND :dateRequestTo IS NOT NULL )) " +
                        "OR (r.CREATE_DATE >= :dateRequestFrom AND :dateRequestFrom IS NOT NULL AND :dateRequestTo IS NULL) " +
                        "OR (r.CREATE_DATE <= :dateRequestTo AND :dateRequestTo IS NOT NULL AND :dateRequestFrom IS NULL ) " +
                        "OR (:dateRequestFrom IS NULL AND :dateRequestTo IS NULL )" +
                        ") " +
                        "AND (sta1.STATUS_CODE =:statusApprove OR :statusApprove IS NULL)  "
                ,
                resultSetMapping = "RequestListMapping"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "RequestListMapping", classes = {
                @ConstructorResult(targetClass = RequestData.class,
                        columns = {
                                @ColumnResult(name = "REQUEST_ID", type = Integer.class),
                                @ColumnResult(name = "REQUEST_YEAR", type = String.class),
                                @ColumnResult(name = "REQUEST_DATE", type = Date.class),
                                @ColumnResult(name = "DATE_INTERNSHIP", type = String.class),
                                @ColumnResult(name = "STUDENT_CODE", type = String.class),
                                @ColumnResult(name = "STUDENT_NAME", type = String.class),
                                @ColumnResult(name = "COMPANY_NAME", type = String.class),
                                @ColumnResult(name = "STATUS_APPROVE", type = String.class),
                                @ColumnResult(name = "APPROVE_DATE", type = Date.class),
                                @ColumnResult(name = "APPROVE_BY", type = String.class),
                                @ColumnResult(name = "STATUS_INFO", type = String.class),
                                @ColumnResult(name = "CREATE_DATE", type = Date.class),
                                @ColumnResult(name = "CREATE_BY", type = String.class),
                                @ColumnResult(name = "UPDATE_DATE", type = Date.class),
                                @ColumnResult(name = "UPDATE_BY", type = String.class),

                        }
                )
        })
})
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REQUEST_ID")
    private Integer requestId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_FROM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFrom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATE_TO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTo;
    @Column(name = "APPROVE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date approveDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @JoinColumn(name = "STUDENT_ID", referencedColumnName = "STUDENT_ID")
    @ManyToOne(optional = false)
    private Student studentId;
    @JoinColumn(name = "CREATE_BY", referencedColumnName = "STUDENT_ID")
    @ManyToOne(optional = false)
    private Student createBy;
    @JoinColumn(name = "UPDATE_BY", referencedColumnName = "STUDENT_ID")
    @ManyToOne
    private Student updateBy;
    @JoinColumn(name = "APPROVE_BY", referencedColumnName = "TEACHER_ID")
    @ManyToOne
    private Teacher approveBy;
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID")
    @ManyToOne(optional = false)
    private Company companyId;
    @JoinColumn(name = "STATUS_INFO", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusInfo;
    @JoinColumn(name = "STATUS_APPROVE", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusApprove;
    @JoinColumn(name = "SCHEDULE_ID", referencedColumnName = "SCHEDULE_ID")
    @ManyToOne(optional = false)
    private Schedule scheduleId;

    public Request() {
    }

    public Request(Integer requestId) {
        this.requestId = requestId;
    }

    public Request(Integer requestId, Date dateFrom, Date dateTo, Date createDate) {
        this.requestId = requestId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.createDate = createDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (requestId != null ? requestId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.requestId == null && other.requestId != null) || (this.requestId != null && !this.requestId.equals(other.requestId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demo.backend.ManageInternship.model.entity.Request[ requestId=" + requestId + " ]";
    }
    
}
