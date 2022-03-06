package it.polimi.progettodb2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class FeeperiodEntityPK implements Serializable {
    @Column(name = "refPackage")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refPackage;
    @Column(name = "period")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int period;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeeperiodEntityPK that = (FeeperiodEntityPK) o;

        if (refPackage != that.refPackage) return false;
        if (period != that.period) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refPackage;
        result = 31 * result + period;
        return result;
    }
}
