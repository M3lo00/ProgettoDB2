package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "payment", schema = "dbproj", catalog = "")
public class PaymentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPayments")
    private int idPayments;
    @Basic
    @Column(name = "refOrder")
    private int refOrder;
    @Basic
    @Column(name = "refUser")
    private int refUser;
    @Basic
    @Column(name = "status")
    private byte status;
    @Basic
    @Column(name = "payTime")
    private Timestamp payTime;


//da sistemare
    @OneToMany(mappedBy = "paymentByRefLastRejection")
    private Collection<AuditEntity> auditsByIdPayments;
//*****************************************************

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    )
    @JoinColumn(name = "refOrder", referencedColumnName = "idOrder", nullable = false)
    private OrderEntity paymentRelatedToOrder;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    )
    @JoinColumn(name = "refUser", referencedColumnName = "idUser", nullable = false)
    private UserEntity paymentRelatedToUser;

    public int getIdPayments() {
        return idPayments;
    }

    public void setIdPayments(int idPayments) {
        this.idPayments = idPayments;
    }

    public int getRefOrder() {
        return refOrder;
    }

    public void setRefOrder(int refOrder) {
        this.refOrder = refOrder;
    }

    public int getRefUser() {
        return refUser;
    }

    public void setRefUser(int refUser) {
        this.refUser = refUser;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentEntity that = (PaymentEntity) o;

        if (idPayments != that.idPayments) return false;
        if (refOrder != that.refOrder) return false;
        if (refUser != that.refUser) return false;
        if (status != that.status) return false;
        if (payTime != null ? !payTime.equals(that.payTime) : that.payTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPayments;
        result = 31 * result + refOrder;
        result = 31 * result + refUser;
        result = 31 * result + (int) status;
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        return result;
    }

    public Collection<AuditEntity> getAuditsByIdPayments() {
        return auditsByIdPayments;
    }

    public void setAuditsByIdPayments(Collection<AuditEntity> auditsByIdPayments) {
        this.auditsByIdPayments = auditsByIdPayments;
    }

    public OrderEntity getOrderByRefOrder() {
        return paymentRelatedToOrder;
    }

    public void setOrderByRefOrder(OrderEntity orderByRefOrder) {
        this.paymentRelatedToOrder = orderByRefOrder;
    }

    public UserEntity getUserByRefUser() {
        return paymentRelatedToUser;
    }

    public void setUserByRefUser(UserEntity userByRefUser) {
        this.paymentRelatedToUser = userByRefUser;
    }
}
