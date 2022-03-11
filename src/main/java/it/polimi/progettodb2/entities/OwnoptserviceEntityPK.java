package it.polimi.progettodb2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class OwnoptserviceEntityPK implements Serializable {
    @Column(name = "refPack")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refPack;
    @Column(name = "refOptService")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refOptService;

    public int getRefPack() {
        return refPack;
    }

    public void setRefPack(int refPack) {
        this.refPack = refPack;
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

        if (refPack != that.refPack) return false;
        if (refOptService != that.refOptService) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refPack;
        result = 31 * result + refOptService;
        return result;
    }
}
