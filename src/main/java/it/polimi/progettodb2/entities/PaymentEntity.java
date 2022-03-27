package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity

//@NamedQuery(name = "Payment.getById",
 //       query = "SELECT r FROM PaymentEntity r  WHERE r.idPayments = ?1")
@NamedQuery(name = "Payment.getById",
        query = "SELECT r FROM PaymentEntity r  WHERE r.idPayments =: idPayments")

@NamedQuery(name = "Payment.UserPayment",
        query ="SELECT r FROM PaymentEntity r JOIN r.refUser u WHERE r.refUser=:user")

@Table(name = "payment", schema = "dbproj")
public class PaymentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPayments")
    private int idPayments;
    
    @Column(name = "refOrder")
    private int refOrder;
    
    @Column(name = "status")
    private byte status;
    
    @Column(name = "payTime")
    private Timestamp payTime;




    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "refUser", referencedColumnName = "idUser")
    private UserEntity refUser;

    public void setIdPayments(int idPayments) {
        this.idPayments = idPayments;
    }

    public int getIdPayments() {
        return idPayments;
    }

    public void getUser(){
        return;
    }

    public int getRefOrder() {
        return refOrder;
    }

    public void setRefOrder(int refOrder) {
        this.refOrder = refOrder;
    }
/*
    public UserEntity getRefUser() {
        return refUser;
    }

    public void setRefUser(UserEntity refUser) {
        this.refUser = refUser;
    }*/

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
       //if (refUser != that.refUser) return false;
        if (status != that.status) return false;
        if (payTime != null ? !payTime.equals(that.payTime) : that.payTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPayments;
        result = 31 * result + refOrder;
        //result = 31 * result + (refUser != null ? refUser.hashCode() : 0);
        result = 31 * result + (int) status;
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        return result;
    }
}
