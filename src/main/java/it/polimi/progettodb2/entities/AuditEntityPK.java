package it.polimi.progettodb2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class AuditEntityPK implements Serializable {
    @Column(name = "refUser")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refUser;
    @Column(name = "refLastRejection")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refLastRejection;

    public int getRefUser() {
        return refUser;
    }

    public void setRefUser(int refUser) {
        this.refUser = refUser;
    }

    public int getRefLastRejection() {
        return refLastRejection;
    }

    public void setRefLastRejection(int refLastRejection) {
        this.refLastRejection = refLastRejection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditEntityPK that = (AuditEntityPK) o;

        if (refUser != that.refUser) return false;
        if (refLastRejection != that.refLastRejection) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refUser;
        result = 31 * result + refLastRejection;
        return result;
    }
}
