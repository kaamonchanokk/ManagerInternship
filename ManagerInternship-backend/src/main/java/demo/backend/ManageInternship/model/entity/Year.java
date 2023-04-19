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
import javax.validation.constraints.NotNull;

/**
 *
 * @author Kamonchanok
 */
@Entity
@Table(name = "year")
@NamedQueries({
    @NamedQuery(name = "Year.findAll", query = "SELECT y FROM Year y")})
public class Year implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "YEAR_ID")
    private Integer yearId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "YEAR_NUMBER")
    private int yearNumber;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentYear")
    private List<Student> studentList;
    @JoinColumn(name = "CREATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne
    private Staff createBy;
    @JoinColumn(name = "UPDATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne
    private Staff updateBy;
    @JoinColumn(name = "STATUS_INFO", referencedColumnName = "STATUS_ID")
    @ManyToOne
    private Status statusInfo;

    public Year() {
    }

    public Year(Integer yearId) {
        this.yearId = yearId;
    }

    public Year(Integer yearId, int yearNumber) {
        this.yearId = yearId;
        this.yearNumber = yearNumber;
    }

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public int getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(int yearNumber) {
        this.yearNumber = yearNumber;
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

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public Staff getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Staff createBy) {
        this.createBy = createBy;
    }

    public Staff getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Staff updateBy) {
        this.updateBy = updateBy;
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
        hash += (yearId != null ? yearId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Year)) {
            return false;
        }
        Year other = (Year) object;
        if ((this.yearId == null && other.yearId != null) || (this.yearId != null && !this.yearId.equals(other.yearId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demo.backend.ManageInternship.model.entity.Year[ yearId=" + yearId + " ]";
    }
    
}
