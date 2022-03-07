package it.polimi.progettodb2.entities;
/*
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "order", schema = "dbproj")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idOrder")
    private int idOrder;
    @Basic
    @Column(name = "refUser")
    private int refUser;
    @Basic
    @Column(name = "refPack")
    private int refPack;
    @Basic
    @Column(name = "period")
    private int period;
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

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
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

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (idOrder != that.idOrder) return false;
        if (refUser != that.refUser) return false;
        if (refPack != that.refPack) return false;
        if (period != that.period) return false;
        if (totalAmount != that.totalAmount) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (valid != null ? !valid.equals(that.valid) : that.valid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOrder;
        result = 31 * result + refUser;
        result = 31 * result + refPack;
        result = 31 * result + period;
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        result = 31 * result + totalAmount;
        return result;
    }
}

 */
