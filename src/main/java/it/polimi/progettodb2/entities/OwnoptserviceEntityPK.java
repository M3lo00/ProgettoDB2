package it.polimi.progettodb2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class OwnoptserviceEntityPK implements Serializable {
    @Column(name = "refServPack")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refServPack;
    @Column(name = "refOptService")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refOptService;

    public int getRefServPack() {
        return refServPack;
    }

    public void setRefServPack(int refServPack) {
        this.refServPack = refServPack;
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

        OwnoptserviceEntityPK that = (OwnoptserviceEntityPK) o;

        if (refServPack != that.refServPack) return false;
        if (refOptService != that.refOptService) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refServPack;
        result = 31 * result + refOptService;
        return result;
    }
}
