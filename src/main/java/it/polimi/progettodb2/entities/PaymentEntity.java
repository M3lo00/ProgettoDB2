package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "payment", schema = "dbproj")
public class PaymentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPayments")
    private int idPayments;
    
    @Column(name = "refOrder")
    private int refOrder;
    
    @Column(name = "refUser")
    private int refUser;
    
    @Column(name = "status")
    private byte status;
    
    @Column(name = "payTime")
    private Timestamp payTime;

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
}
