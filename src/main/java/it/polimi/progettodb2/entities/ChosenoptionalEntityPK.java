package it.polimi.progettodb2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class ChosenoptionalEntityPK implements Serializable {
    @Column(name = "refOrder")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refOrder;
    @Column(name = "refOptService")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refOptService;

    public int getRefOrder() {
        return refOrder;
    }

    public void setRefOrder(int refOrder) {
        this.refOrder = refOrder;
    }

    public int getRefOptService() {
        return refOptService;
    }

    public void setRefOptService(int refOptService) {
        this.refOptService = refOptService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChosenoptionalEntityPK that = (ChosenoptionalEntityPK) o;

        if (refOrder != that.refOrder) return false;
        if (refOptService != that.refOptService) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refOrder;
        result = 31 * result + refOptService;
        return result;
    }
}
