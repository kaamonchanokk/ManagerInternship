/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo.backend.ManageInternship.model.entity;

import demo.backend.ManageInternship.model.bean.StaffList;
import demo.backend.ManageInternship.model.bean.TeacherData;
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
@Table(name = "teacher")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Teacher.getTeacherList",
                query =  "SELECT t.TEACHER_ID,t.TEACHER_CODE," +
                        "CONCAT(t.TEACHER_TITLE,t.TEACHER_NAME,' ',t.TEACHER_LASTNAME) AS TEACHER_NAME " +
                        ",st.STATUS_NAME AS STATUS_TEACHER " +
                        ",f.FACULTY_NAME  " +
                        ",d.DEP_NAME " +
                        ",s.STATUS_NAME " +
                        ",t.CREATE_DATE " +
                        ",CONCAT(sta1.STAFF_TITLE,sta1.STAFF_NAME,' ',sta1.STAFF_LASTNAME) AS CREATE_BY  " +
                        ",t.UPDATE_DATE" +
                        ",CONCAT(sta2.STAFF_TITLE,sta2.STAFF_NAME,' ',sta2.STAFF_LASTNAME) AS UPDATE_BY  " +
                        "FROM teacher t " +
                        "INNER JOIN status s on t.STATUS_INFO = s.STATUS_ID " +
                        "INNER JOIN status st on t.STATUS_TEACHER = st.STATUS_ID " +
                        "INNER JOIN department d on t.DEP_ID = d.DEP_ID  " +
                        "INNER JOIN faculty f on d.FACULTY_ID = f.FACULTY_ID " +
                        "INNER JOIN staff sta1 on t.CREATE_BY = sta1.STAFF_ID " +
                        "LEFT JOIN staff sta2 on t.UPDATE_BY = sta2.STAFF_ID " +
                        "WHERE  (t.TEACHER_CODE = :teacherCode OR :teacherCode IS NULL) " +
                        "AND (t.TEACHER_NAME LIKE CONCAT('%', :teacherName, '%') OR :teacherName IS NULL) " +
                        "AND (t.TEACHER_LASTNAME LIKE CONCAT('%',:teacherLastName, '%') OR :teacherLastName IS NULL) " +
                        "AND (d.DEP_NAME LIKE CONCAT('%',:departmentName, '%') OR :departmentName IS NULL) " +
                        "AND (f.FACULTY_NAME LIKE CONCAT('%',:facultyName, '%') OR :facultyName IS NULL) "
                ,
                resultSetMapping = "TeacherListMapping"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "TeacherListMapping", classes = {
                @ConstructorResult(targetClass = TeacherData.class,
                        columns = {
                                @ColumnResult(name = "TEACHER_ID", type = Integer.class),
                                @ColumnResult(name = "TEACHER_CODE", type = String.class),
                                @ColumnResult(name = "TEACHER_NAME", type = String.class),
                                @ColumnResult(name = "STATUS_TEACHER", type = String.class),
                                @ColumnResult(name = "FACULTY_NAME", type = String.class),
                                @ColumnResult(name = "DEP_NAME", type = String.class),
                                @ColumnResult(name = "STATUS_NAME", type = String.class),
                                @ColumnResult(name = "CREATE_DATE", type = Date.class),
                                @ColumnResult(name = "CREATE_BY", type = String.class),
                                @ColumnResult(name = "UPDATE_DATE", type = Date.class),
                                @ColumnResult(name = "UPDATE_BY", type = String.class),
                        }
                )
        })
})
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TEACHER_ID")
    private Integer teacherId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "TEACHER_CODE")
    private String teacherCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TEACHER_TITLE")
    private String teacherTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TEACHER_NAME")
    private String teacherName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TEACHER_LASTNAME")
    private String teacherLastname;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "STATUS_TEACHER")
    @JoinColumn(name = "STATUS_TEACHER", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusTeacher;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @OneToMany(mappedBy = "approveBy")
    private List<Request> requestList;
    @JoinColumn(name = "CREATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne(optional = false)
    private Staff createBy;
    @JoinColumn(name = "UPDATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne
    private Staff updateBy;
    @JoinColumn(name = "STATUS_INFO", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusInfo;
    @JoinColumn(name = "DEP_ID", referencedColumnName = "DEP_ID")
    @ManyToOne(optional = false)
    private Department depId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "advisor")
    private List<Student> studentList;

    public Teacher() {
    }

    public Teacher(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Teacher(Integer teacherId, String teacherCode, String teacherTitle, String teacherName, String teacherLastname, Status statusTeacher, Date createDate) {
        this.teacherId = teacherId;
        this.teacherCode = teacherCode;
        this.teacherTitle = teacherTitle;
        this.teacherName = teacherName;
        this.teacherLastname = teacherLastname;
        this.statusTeacher = statusTeacher;
        this.createDate = createDate;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (teacherId != null ? teacherId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Teacher)) {
            return false;
        }
        Teacher other = (Teacher) object;
        if ((this.teacherId == null && other.teacherId != null) || (this.teacherId != null && !this.teacherId.equals(other.teacherId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demo.backend.ManageInternship.model.entity.Teacher[ teacherId=" + teacherId + " ]";
    }
    
}
