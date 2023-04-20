/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo.backend.ManageInternship.model.entity;

import demo.backend.ManageInternship.model.bean.DepartmentData;
import demo.backend.ManageInternship.model.bean.FacultyData;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "department")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Department.getDepartmentList",
                query = "SELECT d.DEP_ID,d.DEP_CODE,d.DEP_NAME,f.FACULTY_NAME " +
                        ",s.STATUS_NAME" +
                        ",d.CREATE_DATE" +
                        ",CONCAT(sf1.STAFF_TITLE,sf1.STAFF_NAME,' ',sf1.STAFF_LASTNAME) AS CREATE_BY " +
                        ",f.UPDATE_DATE  " +
                        ",CONCAT(sf2.STAFF_TITLE,sf2.STAFF_NAME,' ',sf2.STAFF_LASTNAME) AS UPDATE_BY   " +
                        " FROM department d " +
                        " INNER JOIN faculty f on f.FACULTY_ID = d.FACULTY_ID " +
                        "INNER JOIN status s on d.STATUS_INFO = s.STATUS_ID " +
                        "INNER JOIN staff sf1 on d.CREATE_BY = sf1.STAFF_ID " +
                        "LEFT JOIN staff sf2 on d.UPDATE_BY = sf2.STAFF_ID " +
                        "WHERE (d.DEP_CODE = :depCode OR :depCode IS NULL) " +
                        "AND (d.DEP_CODE = :depName OR :depName IS NULL) " +
                        "AND (f.FACULTY_NAME = :facultyName OR :facultyName IS NULL) "
                ,
                resultSetMapping = "DepartmentListMapping"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "DepartmentListMapping", classes = {
                @ConstructorResult(targetClass = DepartmentData.class,
                        columns = {
                                @ColumnResult(name = "DEP_ID", type = Integer.class),
                                @ColumnResult(name = "DEP_CODE", type = String.class),
                                @ColumnResult(name = "DEP_NAME", type = String.class),
                                @ColumnResult(name = "FACULTY_NAME", type = String.class),
                                @ColumnResult(name = "STATUS_NAME", type = String.class),
                                @ColumnResult(name = "CREATE_DATE", type = Date.class),
                                @ColumnResult(name = "CREATE_BY", type = String.class),
                                @ColumnResult(name = "UPDATE_DATE", type = Date.class),
                                @ColumnResult(name = "UPDATE_BY", type = String.class),

                        }
                )
        })
})
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DEP_ID")
    private Integer depId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "DEP_CODE")
    private String depCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DEP_NAME")
    private String depName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "depId")
    private List<Teacher> teacherList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "depId")
    private List<Student> studentList;
    @JoinColumn(name = "FACULTY_ID", referencedColumnName = "FACULTY_ID")
    @ManyToOne(optional = false)
    private Faculty facultyId;
    @JoinColumn(name = "CREATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne(optional = false)
    private Staff createBy;
    @JoinColumn(name = "UPDATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne
    private Staff updateBy;
    @JoinColumn(name = "STATUS_INFO", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusInfo;

    public Department() {
    }

    public Department(Integer depId) {
        this.depId = depId;
    }

    public Department(Integer depId, String depCode, String depName, Date createDate) {
        this.depId = depId;
        this.depCode = depCode;
        this.depName = depName;
        this.createDate = createDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depId != null ? depId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.depId == null && other.depId != null) || (this.depId != null && !this.depId.equals(other.depId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demo.backend.ManageInternship.model.entity.Department[ depId=" + depId + " ]";
    }
    
}
