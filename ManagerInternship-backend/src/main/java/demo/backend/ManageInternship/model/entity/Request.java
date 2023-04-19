/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo.backend.ManageInternship.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Kamonchanok
 */
@Entity
@Table(name = "request")
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r")})
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

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Student getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Student createBy) {
        this.createBy = createBy;
    }

    public Student getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Student updateBy) {
        this.updateBy = updateBy;
    }

    public Teacher getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(Teacher approveBy) {
        this.approveBy = approveBy;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public Status getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(Status statusInfo) {
        this.statusInfo = statusInfo;
    }

    public Status getStatusApprove() {
        return statusApprove;
    }

    public void setStatusApprove(Status statusApprove) {
        this.statusApprove = statusApprove;
    }

    public Schedule getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Schedule scheduleId) {
        this.scheduleId = scheduleId;
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
