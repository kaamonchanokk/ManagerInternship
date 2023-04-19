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
import javax.validation.constraints.Size;

/**
 *
 * @author Kamonchanok
 */
@Entity
@Table(name = "student")
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "STUDENT_ID")
    private Integer studentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "STUDENT_CODE")
    private String studentCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "STUDENT_TITLE")
    private String studentTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "STUDENT_NAME")
    private String studentName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "STUDENT_LASTNAME")
    private String studentLastname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "STUDENT_GRADE")
    private double studentGrade;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private List<Request> requestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createBy")
    private List<Request> requestList1;
    @OneToMany(mappedBy = "updateBy")
    private List<Request> requestList2;
    @JoinColumn(name = "STATUS_STUDENT", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusStudent;
    @JoinColumn(name = "STATUS_INFO", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusInfo;
    @JoinColumn(name = "ADVISOR", referencedColumnName = "TEACHER_ID")
    @ManyToOne(optional = false)
    private Teacher advisor;
    @JoinColumn(name = "CREATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne
    private Staff createBy;
    @JoinColumn(name = "UPDATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne
    private Staff updateBy;
    @JoinColumn(name = "DEP_ID", referencedColumnName = "DEP_ID")
    @ManyToOne(optional = false)
    private Department depId;
    @JoinColumn(name = "STUDENT_YEAR", referencedColumnName = "YEAR_ID")
    @ManyToOne(optional = false)
    private Year studentYear;

    public Student() {
    }

    public Student(Integer studentId) {
        this.studentId = studentId;
    }

    public Student(Integer studentId, String studentCode, String studentTitle, String studentName, String studentLastname, double studentGrade) {
        this.studentId = studentId;
        this.studentCode = studentCode;
        this.studentTitle = studentTitle;
        this.studentName = studentName;
        this.studentLastname = studentLastname;
        this.studentGrade = studentGrade;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentTitle() {
        return studentTitle;
    }

    public void setStudentTitle(String studentTitle) {
        this.studentTitle = studentTitle;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentLastname() {
        return studentLastname;
    }

    public void setStudentLastname(String studentLastname) {
        this.studentLastname = studentLastname;
    }

    public double getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(double studentGrade) {
        this.studentGrade = studentGrade;
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

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public List<Request> getRequestList1() {
        return requestList1;
    }

    public void setRequestList1(List<Request> requestList1) {
        this.requestList1 = requestList1;
    }

    public List<Request> getRequestList2() {
        return requestList2;
    }

    public void setRequestList2(List<Request> requestList2) {
        this.requestList2 = requestList2;
    }

    public Status getStatusStudent() {
        return statusStudent;
    }

    public void setStatusStudent(Status statusStudent) {
        this.statusStudent = statusStudent;
    }

    public Status getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(Status statusInfo) {
        this.statusInfo = statusInfo;
    }

    public Teacher getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Teacher advisor) {
        this.advisor = advisor;
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

    public Department getDepId() {
        return depId;
    }

    public void setDepId(Department depId) {
        this.depId = depId;
    }

    public Year getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(Year studentYear) {
        this.studentYear = studentYear;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demo.backend.ManageInternship.model.entity.Student[ studentId=" + studentId + " ]";
    }
    
}
