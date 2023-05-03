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
                        "AND (st.STAFF_NAME LIKE CONCAT('%',:staffName,'%') OR :staffName IS NULL) " +
                        "AND (st.STAFF_LASTNAME LIKE CONCAT('%',:staffLastName,'%') OR :staffLastName IS NULL) " +
                        "AND (p.POS_NAME LIKE CONCAT('%',:staffPosition,'%') OR :staffPosition IS NULL)" +
                        "AND s.STATUS_CODE = 'AC' "
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

    
}
