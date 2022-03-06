package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "feeperiod", schema = "dbproj")
@IdClass(FeeperiodEntityPK.class)
public class FeeperiodEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "refPackage")
    private int refPackage;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "period")
    private int period;
    @Basic
    @Column(name = "fee")
    private Integer fee;

    public int getRefPackage() {
        return refPackage;
    }

    public void setRefPackage(int refPackage) {
        this.refPackage = refPackage;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeeperiodEntity that = (FeeperiodEntity) o;

        if (refPackage != that.refPackage) return false;
        if (period != that.period) return false;
        if (fee != null ? !fee.equals(that.fee) : that.fee != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refPackage;
        result = 31 * result + period;
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        return result;
    }
}
