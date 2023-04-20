/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo.backend.ManageInternship.model.entity;

import demo.backend.ManageInternship.model.bean.PositionData;
import demo.backend.ManageInternship.model.bean.StaffList;
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
@Table(name = "`position`")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Position.getPositionList",
                query = "SELECT p.POS_ID,p.POS_CODE,p.POS_NAME,s.STATUS_NAME, " +
                        "p.CREATE_DATE ,CONCAT(st.STAFF_TITLE,st.STAFF_NAME,' ',st.STAFF_LASTNAME) AS CREATE_BY ," +
                        "p.UPDATE_DATE " +
                        ",p.UPDATE_BY " +
                        "FROM position p "+
                        "INNER JOIN status s on p.STATUS_INFO = s.STATUS_ID " +
                        "INNER JOIN staff st on p.CREATE_BY = st.STAFF_ID " +
                        "LEFT JOIN staff st2 on p.UPDATE_BY = st2.STAFF_ID "+
                        "WHERE (p.POS_CODE = :positionCode OR :positionCode IS NULL) " +
                        "AND (p.POS_NAME = :positionName OR :positionName IS NULL) "
//                        "AND (st.STAFF_NAME = :staffName OR :staffName IS NULL) " +
//                        "AND (st.STAFF_LASTNAME = :staffLastName OR :staffLastName IS NULL) " +
//                        "AND (p.POS_NAME = :staffPosition OR :staffPosition IS NULL) "
                ,
                resultSetMapping = "PositionListMapping"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "PositionListMapping", classes = {
                @ConstructorResult(targetClass = PositionData.class,
                        columns = {
                                @ColumnResult(name = "POS_ID", type = Integer.class),
                                @ColumnResult(name = "POS_CODE", type = String.class),
                                @ColumnResult(name = "POS_NAME", type = String.class),
                                @ColumnResult(name = "STATUS_NAME", type = String.class),
                                @ColumnResult(name = "CREATE_DATE", type = Date.class),
                                @ColumnResult(name = "CREATE_BY", type = String.class),
                                @ColumnResult(name = "UPDATE_DATE", type = Date.class),
                                @ColumnResult(name = "UPDATE_BY", type = String.class),

                        }
                )
        })
})
public class Position implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "POS_CODE")
    private String posCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "POS_NAME")
    private String posName;
//    @Basic(optional = false)
//    @NotNull
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "POS_ID")
    private Integer posId;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "posId")
    private List<Staff> staffList;
    @JoinColumn(name = "CREATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne(optional = false)
    private Staff createBy;
    @JoinColumn(name = "UPDATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne
    private Staff updateBy;
    @JoinColumn(name = "STATUS_INFO", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusInfo;

    public Position() {
    }

    public Position(Integer posId) {
        this.posId = posId;
    }

    public Position(Integer posId, String posCode, String posName, Date createDate) {
        this.posId = posId;
        this.posCode = posCode;
        this.posName = posName;
        this.createDate = createDate;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
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

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
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
        hash += (posId != null ? posId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if ((this.posId == null && other.posId != null) || (this.posId != null && !this.posId.equals(other.posId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demo.backend.ManageInternship.model.entity.Position[ posId=" + posId + " ]";
    }
    
}
