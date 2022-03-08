package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "order", schema = "dbproj", catalog = "")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idOrder")
    private Integer idOrder;
    @Basic
    @Column(name = "refUser")
    private int refUser;
    @Basic
    @Column(name = "refPack")
    private int refPack;
    @Basic
    @Column(name = "creationDate")
    private Timestamp creationDate;
    @Basic
    @Column(name = "startDate")
    private Date startDate;
    @Basic
    @Column(name = "valid")
    private Object valid;
    @Basic
    @Column(name = "totalAmount")
    private int totalAmount;
    @Basic
    @Column(name = "nMobile")
    private Integer nMobile;
    @Basic
    @Column(name = "nFixed")
    private Integer nFixed;

    @ManyToOne (fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    )
    @JoinColumn(name = "refUser", referencedColumnName = "idUser", nullable = false)
    private UserEntity userEntity;

    @ManyToOne (fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    )
    @JoinColumn(name = "refPack", referencedColumnName = "idPackage", nullable = false)
    private PackageEntity packageEntity;

    @OneToMany(mappedBy = "refOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<PaymentEntity> payments;

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public int getRefUser() {
        return refUser;
    }

    public void setRefUser(int refUser) {
        this.refUser = refUser;
    }

    public int getRefPack() {
        return refPack;
    }

    public void setRefPack(int refPack) {
        this.refPack = refPack;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Object getValid() {
        return valid;
    }

    public void setValid(Object valid) {
        this.valid = valid;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getnMobile() {
        return nMobile;
    }

    public void setnMobile(Integer nMobile) {
        this.nMobile = nMobile;
    }

    public Integer getnFixed() {
        return nFixed;
    }

    public void setnFixed(Integer nFixed) {
        this.nFixed = nFixed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (refUser != that.refUser) return false;
        if (refPack != that.refPack) return false;
        if (totalAmount != that.totalAmount) return false;
        if (idOrder != null ? !idOrder.equals(that.idOrder) : that.idOrder != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (valid != null ? !valid.equals(that.valid) : that.valid != null) return false;
        if (nMobile != null ? !nMobile.equals(that.nMobile) : that.nMobile != null) return false;
        if (nFixed != null ? !nFixed.equals(that.nFixed) : that.nFixed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOrder != null ? idOrder.hashCode() : 0;
        result = 31 * result + refUser;
        result = 31 * result + refPack;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        result = 31 * result + totalAmount;
        result = 31 * result + (nMobile != null ? nMobile.hashCode() : 0);
        result = 31 * result + (nFixed != null ? nFixed.hashCode() : 0);
        return result;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public PackageEntity getPackageEntity() {
        return packageEntity;
    }

    public void setPackageEntity(PackageEntity packageEntity) {
        this.packageEntity = packageEntity;
    }

    public Collection<PaymentEntity> getPayments() {
        return payments;
    }

    public void setPayments(Collection<PaymentEntity> payments) {
        this.payments = payments;
    }
}
