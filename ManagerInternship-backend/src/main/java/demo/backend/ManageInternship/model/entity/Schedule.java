/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo.backend.ManageInternship.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Kamonchanok
 */
@Entity
@Table(name = "schedule")
@NamedQueries({
    @NamedQuery(name = "Schedule.findAll", query = "SELECT s FROM Schedule s")})
public class Schedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SCHEDULE_ID")
    private Integer scheduleId;
    @Column(name = "SCHEDULE_YEAR")
    private Integer scheduleYear;
    @Column(name = "SCHEDULE_SEMESTER")
    private Integer scheduleSemester;
    @Column(name = "SCHEDULE_FROM")
    @Temporal(TemporalType.DATE)
    private Date scheduleFrom;
    @Column(name = "SCHEDULE_TO")
    @Temporal(TemporalType.DATE)
    private Date scheduleTo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "scheduleId")
    private List<Request> requestList;
    @JoinColumn(name = "STATUS_INFO", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusInfo;

    public Schedule() {
    }

    public Schedule(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getScheduleYear() {
        return scheduleYear;
    }

    public void setScheduleYear(Integer scheduleYear) {
        this.scheduleYear = scheduleYear;
    }

    public Integer getScheduleSemester() {
        return scheduleSemester;
    }

    public void setScheduleSemester(Integer scheduleSemester) {
        this.scheduleSemester = scheduleSemester;
    }

    public Date getScheduleFrom() {
        return scheduleFrom;
    }

    public void setScheduleFrom(Date scheduleFrom) {
        this.scheduleFrom = scheduleFrom;
    }

    public Date getScheduleTo() {
        return scheduleTo;
    }

    public void setScheduleTo(Date scheduleTo) {
        this.scheduleTo = scheduleTo;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public Status getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(Status statusInfo) {
        this.statusInfo = statusInfo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (scheduleId != null ? scheduleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedule)) {
            return false;
        }
        Schedule other = (Schedule) object;
        if ((this.scheduleId == null && other.scheduleId != null) || (this.scheduleId != null && !this.scheduleId.equals(other.scheduleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demo.backend.ManageInternship.model.entity.Schedule[ scheduleId=" + scheduleId + " ]";
    }
    
}
