/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo.backend.ManageInternship.model.entity;

import demo.backend.ManageInternship.model.bean.StaffList;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Kamonchanok
 */
@Data
@Entity
@Table(name = "staff")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Staff.getStaffList",
                query = "SELECT st.STAFF_ID,st.STAFF_CODE,CONCAT(st.STAFF_TITLE,st.STAFF_NAME,' ',st.STAFF_LASTNAME) AS STAFF_NAME,p.POS_NAME AS STAFF_POSITION,s.STATUS_NAME AS STATUS_INFO FROM staff st " +
                        "INNER JOIN status s on st.STATUS_INFO = s.STATUS_ID AND s.STATUS_TYPE = 'STATUS_INFO ' "+
                        "INNER JOIN position p on st.POS_ID = p.POS_ID "+
                        "WHERE  (st.STAFF_ID = :staffId OR :staffId IS NULL) " +
                        "AND  (st.STAFF_CODE = :staffCode OR :staffCode IS NULL)" +
                        "AND (st.STAFF_NAME = :staffName OR :staffName IS NULL) " +
                        "AND (st.STAFF_LASTNAME = :staffLastName OR :staffLastName IS NULL) " +
                        "AND (p.POS_NAME = :staffPosition OR :staffPosition IS NULL) "
                ,
                resultSetMapping = "StaffListMapping"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "StaffListMapping", classes = {
                @ConstructorResult(targetClass = StaffList.class,
                        columns = {
                                @ColumnResult(name = "STAFF_ID", type = Integer.class),
                                @ColumnResult(name = "STAFF_CODE", type = String.class),
                                @ColumnResult(name = "STAFF_NAME", type = String.class),
                                @ColumnResult(name = "STAFF_POSITION", type = String.class),
                                @ColumnResult(name = "STATUS_INFO", type = String.class),
                        }
                )
        })
})
public class Staff implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "STAFF_CODE")
    private String staffCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "STAFF_TITLE")
    private String staffTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "STAFF_NAME")
    private String staffName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "STAFF_LASTNAME")
    private String staffLastname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createBy")
    private List<Teacher> teacherList;
    @OneToMany(mappedBy = "updateBy")
    private List<Teacher> teacherList1;
    @OneToMany(mappedBy = "createBy")
    private List<Student> studentList;
    @OneToMany(mappedBy = "updateBy")
    private List<Student> studentList1;
    @OneToMany(mappedBy = "createBy")
    private List<Year> yearList;
    @OneToMany(mappedBy = "updateBy")
    private List<Year> yearList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createBy")
    private List<Company> companyList;
    @OneToMany(mappedBy = "updateBy")
    private List<Company> companyList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createBy")
    private List<Department> departmentList;
    @OneToMany(mappedBy = "updateBy")
    private List<Department> departmentList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createBy")
    private List<Faculty> facultyList;
    @OneToMany(mappedBy = "updateBy")
    private List<Faculty> facultyList1;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "STAFF_ID")
    private Integer staffId;
    @JoinColumn(name = "STATUS_INFO", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusInfo;
    @JoinColumn(name = "POS_ID", referencedColumnName = "POS_ID")
    @ManyToOne(optional = false)
    private Position posId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createBy")
    private List<Position> positionList;
    @OneToMany(mappedBy = "updateBy")
    private List<Position> positionList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createBy")
    private List<Status> statusList;
    @OneToMany(mappedBy = "updateBy")
    private List<Status> statusList1;

    public Staff() {
    }

    public Staff(Integer staffId) {
        this.staffId = staffId;
    }

    public Staff(Integer staffId, String staffCode, String staffTitle, String staffName, String staffLastname) {
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.staffTitle = staffTitle;
        this.staffName = staffName;
        this.staffLastname = staffLastname;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffTitle() {
        return staffTitle;
    }

    public void setStaffTitle(String staffTitle) {
        this.staffTitle = staffTitle;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffLastname() {
        return staffLastname;
    }

    public void setStaffLastname(String staffLastname) {
        this.staffLastname = staffLastname;
    }

    public Status getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(Status statusInfo) {
        this.statusInfo = statusInfo;
    }

    public Position getPosId() {
        return posId;
    }

    public void setPosId(Position posId) {
        this.posId = posId;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }

    public List<Position> getPositionList1() {
        return positionList1;
    }

    public void setPositionList1(List<Position> positionList1) {
        this.positionList1 = positionList1;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }

    public List<Status> getStatusList1() {
        return statusList1;
    }

    public void setStatusList1(List<Status> statusList1) {
        this.statusList1 = statusList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffId != null ? staffId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.staffId == null && other.staffId != null) || (this.staffId != null && !this.staffId.equals(other.staffId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demo.backend.ManageInternship.model.entity.Staff[ staffId=" + staffId + " ]";
    }
    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public List<Teacher> getTeacherList1() {
        return teacherList1;
    }

    public void setTeacherList1(List<Teacher> teacherList1) {
        this.teacherList1 = teacherList1;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Student> getStudentList1() {
        return studentList1;
    }

    public void setStudentList1(List<Student> studentList1) {
        this.studentList1 = studentList1;
    }

    public List<Year> getYearList() {
        return yearList;
    }

    public void setYearList(List<Year> yearList) {
        this.yearList = yearList;
    }

    public List<Year> getYearList1() {
        return yearList1;
    }

    public void setYearList1(List<Year> yearList1) {
        this.yearList1 = yearList1;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public List<Company> getCompanyList1() {
        return companyList1;
    }

    public void setCompanyList1(List<Company> companyList1) {
        this.companyList1 = companyList1;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Department> getDepartmentList1() {
        return departmentList1;
    }

    public void setDepartmentList1(List<Department> departmentList1) {
        this.departmentList1 = departmentList1;
    }

    public List<Faculty> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<Faculty> facultyList) {
        this.facultyList = facultyList;
    }

    public List<Faculty> getFacultyList1() {
        return facultyList1;
    }

    public void setFacultyList1(List<Faculty> facultyList1) {
        this.facultyList1 = facultyList1;
    }

    
}
