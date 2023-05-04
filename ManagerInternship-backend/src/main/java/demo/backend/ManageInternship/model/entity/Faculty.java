/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo.backend.ManageInternship.model.entity;

import demo.backend.ManageInternship.model.bean.FacultyData;
import demo.backend.ManageInternship.model.bean.PositionData;
import demo.backend.ManageInternship.model.payload.response.report.ReportFacultyResponse;
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
@Table(name = "faculty")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Faculty.getFacultyList",
                query = "SELECT f.FACULTY_ID,f.FACULTY_CODE,f.FACULTY_NAME" +
                        ",s.STATUS_NAME" +
                        ",f.CREATE_DATE" +
                        ",CONCAT(sf1.STAFF_TITLE,sf1.STAFF_NAME,' ',sf1.STAFF_LASTNAME) AS CREATE_BY " +
                        ",f.UPDATE_DATE  " +
                        ",CONCAT(sf2.STAFF_TITLE,sf2.STAFF_NAME,' ',sf2.STAFF_LASTNAME) AS UPDATE_BY   " +
                        " FROM faculty f " +
                        "INNER JOIN status s on f.STATUS_INFO = s.STATUS_ID " +
                        "INNER JOIN staff sf1 on f.CREATE_BY = sf1.STAFF_ID " +
                        "LEFT JOIN staff sf2 on f.UPDATE_BY = sf2.STAFF_ID " +
                        "WHERE (f.FACULTY_CODE = :facultyCode OR :facultyCode IS NULL) " +
                        "AND (f.FACULTY_NAME LIKE CONCAT('%', :facultyName ,'%' ) OR :facultyName IS NULL)" +
                        "AND s.STATUS_CODE = 'AC' "
                ,
                resultSetMapping = "FacultyListMapping"
        ),
        @NamedNativeQuery(
                name = "Faculty.getReportFaculty",
                query = "select t.FACULTY_NAME AS FACULTY_NAME,COUNT(r.REQUEST_ID) AS TOTAL_INTERNSHIP from " +
                        "request r " +
                        "INNER JOIN (SELECT s.STUDENT_ID,f.FACULTY_ID,f.FACULTY_NAME " +
                        "FROM student s " +
                        "INNER JOIN department d on  d.DEP_ID=s.DEP_ID " +
                        "INNER JOIN faculty f on f.FACULTY_ID = d.FACULTY_ID " +
                        "GROUP BY s.STUDENT_ID) AS t ON r.STUDENT_ID = t.STUDENT_ID " +
                        "WHERE r.STATUS_APPROVE = 6 " +
                        "AND  t.FACULTY_NAME LIKE CONCAT('%',:facultyName,'%') OR :facultyName IS NULL " +
                        "GROUP BY t.FACULTY_ID;"
                ,
                resultSetMapping = "FacultyReportMapping"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "FacultyListMapping", classes = {
                @ConstructorResult(targetClass = FacultyData.class,
                        columns = {
                                @ColumnResult(name = "FACULTY_ID", type = Integer.class),
                                @ColumnResult(name = "FACULTY_CODE", type = String.class),
                                @ColumnResult(name = "FACULTY_NAME", type = String.class),
                                @ColumnResult(name = "STATUS_NAME", type = String.class),
                                @ColumnResult(name = "CREATE_DATE", type = Date.class),
                                @ColumnResult(name = "CREATE_BY", type = String.class),
                                @ColumnResult(name = "UPDATE_DATE", type = Date.class),
                                @ColumnResult(name = "UPDATE_BY", type = String.class),

                        }
                )
        }),
        @SqlResultSetMapping(name = "FacultyReportMapping", classes = {
                @ConstructorResult(targetClass = ReportFacultyResponse.class,
                        columns = {
                                @ColumnResult(name = "FACULTY_NAME", type = String.class),
                                @ColumnResult(name = "TOTAL_INTERNSHIP", type = Integer.class)

                        }
                )
        })
})
public class Faculty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FACULTY_ID")
    private Integer facultyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "FACULTY_CODE")
    private String facultyCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FACULTY_NAME")
    private String facultyName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facultyId")
    private List<Department> departmentList;
    @JoinColumn(name = "CREATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne(optional = false)
    private Staff createBy;
    @JoinColumn(name = "UPDATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne
    private Staff updateBy;
    @JoinColumn(name = "STATUS_INFO", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusInfo;

    public Faculty() {
    }

    public Faculty(Integer facultyId) {
        this.facultyId = facultyId;
    }

    public Faculty(Integer facultyId, String facultyCode, String facultyName, Date createDate) {
        this.facultyId = facultyId;
        this.facultyCode = facultyCode;
        this.facultyName = facultyName;
        this.createDate = createDate;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facultyId != null ? facultyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Faculty)) {
            return false;
        }
        Faculty other = (Faculty) object;
        if ((this.facultyId == null && other.facultyId != null) || (this.facultyId != null && !this.facultyId.equals(other.facultyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demo.backend.ManageInternship.model.entity.Faculty[ facultyId=" + facultyId + " ]";
    }
    
}
