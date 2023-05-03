/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo.backend.ManageInternship.model.entity;

import demo.backend.ManageInternship.model.bean.CompanyData;
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
@Table(name = "company")
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Company.getCompanyList",
                query = "SELECT c.COMPANY_ID" +
                        ",c.COMPANY_NAME" +
                        ",c.COMPANY_TELEPHONE " +
                        ",c.COMPANY_ADDRESS " +
                        ",s.STATUS_NAME " +
                        ",c.CREATE_DATE " +
                        ",CONCAT(st1.STAFF_TITLE,st1.STAFF_NAME,' ',st1.STAFF_LASTNAME) AS CREATE_BY " +
                        ",c.UPDATE_DATE " +
                        ",CONCAT(st2.STAFF_TITLE,st2.STAFF_NAME,' ',st2.STAFF_LASTNAME) AS UPDATE_BY " +
                        "FROM company c " +
                        "INNER JOIN status s on c.STATUS_INFO = s.STATUS_ID " +
                        "INNER JOIN staff st1 on st1.STAFF_ID = c.CREATE_BY " +
                        "LEFT JOIN staff st2 on st2.STAFF_ID = c.UPDATE_BY "+
                        "WHERE  (c.COMPANY_NAME LIKE CONCAT('%',:companyName,'%') OR :companyName IS NULL) " +
                        "AND s.STATUS_CODE = 'AC'"
                ,
                resultSetMapping = "CompanyListMapping"
        )
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "CompanyListMapping", classes = {
                @ConstructorResult(targetClass = CompanyData.class,
                        columns = {
                                @ColumnResult(name = "COMPANY_ID", type = Integer.class),
                                @ColumnResult(name = "COMPANY_NAME", type = String.class),
                                @ColumnResult(name = "COMPANY_TELEPHONE", type = String.class),
                                @ColumnResult(name = "COMPANY_ADDRESS", type = String.class),
                                @ColumnResult(name = "STATUS_NAME", type = String.class),
                                @ColumnResult(name = "CREATE_DATE", type = Date.class),
                                @ColumnResult(name = "CREATE_BY", type = String.class),
                                @ColumnResult(name = "UPDATE_DATE", type = Date.class),
                                @ColumnResult(name = "UPDATE_BY", type = String.class),

                        }
                )
        })
})
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COMPANY_ID")
    private Integer companyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "COMPANY_TELEPHONE")
    private String companyTelephone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "COMPANY_ADDRESS")
    private String companyAddress;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "UPDATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private List<Request> requestList;
    @JoinColumn(name = "STATUS_INFO", referencedColumnName = "STATUS_ID")
    @ManyToOne(optional = false)
    private Status statusInfo;
    @JoinColumn(name = "CREATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne(optional = false)
    private Staff createBy;
    @JoinColumn(name = "UPDATE_BY", referencedColumnName = "STAFF_ID")
    @ManyToOne
    private Staff updateBy;

    public Company() {
    }

    public Company(Integer companyId) {
        this.companyId = companyId;
    }

    public Company(Integer companyId, String companyName, String companyTelephone, String companyAddress, Date createDate) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyTelephone = companyTelephone;
        this.companyAddress = companyAddress;
        this.createDate = createDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyId != null ? companyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.companyId == null && other.companyId != null) || (this.companyId != null && !this.companyId.equals(other.companyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "demo.backend.ManageInternship.model.entity.Company[ companyId=" + companyId + " ]";
    }
    
}
