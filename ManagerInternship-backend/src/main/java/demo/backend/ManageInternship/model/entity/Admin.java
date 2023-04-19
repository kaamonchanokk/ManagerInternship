/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo.backend.ManageInternship.model.entity;

import demo.backend.ManageInternship.model.bean.AdminList;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Kamonchanok
 */
@Data
@Entity
@Table(name = "admin")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Admin.getAdminList",
                query = "SELECT a.USER_ID,a.USER_CODE,CONCAT(a.USER_NAME,' ',a.USER_LASTNAME) AS USER_NAME,s.STATUS_NAME AS STATUS_INFO FROM Admin a " +
                        "INNER JOIN status s on a.STATUS_INFO = s.STATUS_ID AND s.STATUS_TYPE = 'STATUS_INFO ' " +
                        "WHERE  (a.USER_ID = :userId OR :userId IS NULL) " +
                        "AND  (a.USER_CODE = :userCode OR :userCode IS NULL)" +
                        "AND (a.USER_NAME = :userName OR :userName IS NULL) " +
                        "AND (a.USER_LASTNAME = :userLastName OR :userLastName IS NULL) ",
                resultSetMapping = "AdminListMapping"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "AdminListMapping", classes = {
                @ConstructorResult(targetClass = AdminList.class,
                        columns = {
                                @ColumnResult(name = "USER_ID", type = Integer.class),
                                @ColumnResult(name = "USER_CODE", type = String.class),
                                @ColumnResult(name = "USER_NAME", type = String.class),
                                @ColumnResult(name = "STATUS_INFO", type = String.class),
                        }
                )
        })
})

public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USER_ID")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "USER_CODE")
    private String userCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USER_NAME")
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "USER_LASTNAME")
    private String userLastname;
    @JoinColumn(name = "STATUS_INFO", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusInfo;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createBy")
//    private List<Status> statusList;
//    @OneToMany(mappedBy = "updateBy")
//    private List<Status> statusList1;

    public Admin() {
    }

    public Admin(Integer userId) {
        this.userId = userId;
    }

    public Admin(Integer userId, String userCode, String userName, String userLastname) {
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
        this.userLastname = userLastname;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public Status getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(Status statusInfo) {
        this.statusInfo = statusInfo;
    }

//    public List<Status> getStatusList() {
//        return statusList;
//    }
//
//    public void setStatusList(List<Status> statusList) {
//        this.statusList = statusList;
//    }
//
//    public List<Status> getStatusList1() {
//        return statusList1;
//    }
//
//    public void setStatusList1(List<Status> statusList1) {
//        this.statusList1 = statusList1;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demo.backend.ManageInternship.model.entity.Admin[ userId=" + userId + " ]";
    }
    
}
