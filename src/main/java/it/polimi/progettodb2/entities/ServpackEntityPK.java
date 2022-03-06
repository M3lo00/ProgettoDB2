package it.polimi.progettodb2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class ServpackEntityPK implements Serializable {
    @Column(name = "refService")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refService;
    @Column(name = "refPackage")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refPackage;

    public int getRefService() {
        return refService;
    }

    public void setRefService(int refService) {
        this.refService = refService;
    }

    public int getRefPackage() {
        return refPackage;
    }

    public void setRefPackage(int refPackage) {
        this.refPackage = refPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServpackEntityPK that = (ServpackEntityPK) o;

        if (refService != that.refService) return false;
        if (refPackage != that.refPackage) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refService;
        result = 31 * result + refPackage;
        return result;
    }
}
