/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo.backend.ManageInternship.model.entity;

import demo.backend.ManageInternship.model.bean.StaffList;
import demo.backend.ManageInternship.model.bean.StudentData;
import lombok.Data;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "student")
@NamedNativeQueries({
        @NamedNativeQuery(
//                private Integer studentId;
//                private String studentCode;
//                private String studentName;
//                private String studentYear;
//                private String departmentName;
//                private String facultyName;
//                private BigDecimal studentGrade;
//                private String statusStudent;
//                private String advisorName;
//                private String statusInfo;
//                private Date createdDate;
//                private String createBy;
//                private Date updateDate;
//                private String updateBy;
                name = "Student.getStudentList",
                query = "SELECT stu.STUDENT_ID" +
                        ",stu.STUDENT_CODE" +
                        ",CONCAT(stu.STUDENT_TITLE,stu.STUDENT_NAME,' ',stu.STUDENT_LASTNAME) AS STUDENT_NAME " +
                        ",y.YEAR_NUMBER" +
                        ",f.FACULTY_NAME" +
                        ",d.DEP_NAME" +
                        ",stu.STUDENT_GRADE" +
                        ",s2.STATUS_NAME" +
                        ",CONCAT(t.TEACHER_TITLE,t.TEACHER_NAME,' ',t.TEACHER_LASTNAME) AS TEACHER_NAME  " +
                        ",s.STATUS_NAME AS STATUS_INFO " +
                        ",stu.CREATE_DATE" +
                        ",CONCAT(sf.STAFF_TITLE,sf.STAFF_NAME,' ',sf.STAFF_LASTNAME) AS CREATE_BY  " +
                        ",stu.UPDATE_DATE " +
                        ",CONCAT(sf2.STAFF_TITLE,sf2.STAFF_NAME,' ',sf2.STAFF_LASTNAME) AS UPDATE_BY  " +
                        "FROM student stu " +
                        "INNER JOIN department d on stu.DEP_ID = d.DEP_ID " +
                        "INNER JOIN faculty f on f.FACULTY_ID = d.FACULTY_ID " +
                        "INNER JOIN status s on stu.STATUS_INFO = s.STATUS_ID " +
                        "INNER JOIN status s2 on s2.STATUS_ID = stu.STATUS_STUDENT " +
                        "INNER JOIN teacher t on t.TEACHER_ID = stu.ADVISOR " +
                        "INNER JOIN year y on y.YEAR_ID = stu.STUDENT_YEAR " +
                        "INNER JOIN staff sf on sf.STAFF_ID =  stu.CREATE_BY " +
                        "LEFT JOIN staff sf2 on sf.STAFF_ID =  stu.UPDATE_BY " +
                        "WHERE  (stu.STUDENT_CODE = :studentCode OR :studentCode IS NULL) " +
                        "AND  (stu.STUDENT_NAME = :studentName OR :studentName IS NULL)" +
                        "AND (stu.STUDENT_LASTNAME = :studentLastName OR :studentLastName IS NULL) " +
                        "AND (d.DEP_NAME = :departmentName OR :departmentName IS NULL) " +
                        "AND (f.FACULTY_NAME = :facultyName OR :facultyName IS NULL) "
                ,
                resultSetMapping = "StudentListMapping"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "StudentListMapping", classes = {
                @ConstructorResult(targetClass = StudentData.class,
                        columns = {
                                @ColumnResult(name = "STUDENT_ID", type = Integer.class),
                                @ColumnResult(name = "STUDENT_CODE", type = String.class),
                                @ColumnResult(name = "STUDENT_NAME", type = String.class),
                                @ColumnResult(name = "YEAR_NUMBER", type = String.class),
                                @ColumnResult(name = "FACULTY_NAME", type = String.class),
                                @ColumnResult(name = "DEP_NAME", type = String.class),
                                @ColumnResult(name = "STUDENT_GRADE", type = BigDecimal.class),
                                @ColumnResult(name = "STATUS_NAME", type = String.class),
                                @ColumnResult(name = "TEACHER_NAME", type = String.class),
                                @ColumnResult(name = "STATUS_INFO", type = String.class),
                                @ColumnResult(name = "CREATE_DATE", type = Date.class),
                                @ColumnResult(name = "CREATE_BY", type = String.class),
                                @ColumnResult(name = "UPDATE_DATE", type = Date.class),
                                @ColumnResult(name = "UPDATE_BY", type = String.class),
                        }
                )
        })
})
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
