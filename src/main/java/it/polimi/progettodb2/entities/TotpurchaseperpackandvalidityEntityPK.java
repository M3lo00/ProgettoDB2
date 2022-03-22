package it.polimi.progettodb2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class TotpurchaseperpackandvalidityEntityPK implements Serializable {
    @Column(name = "package_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int packageId;
    @Column(name = "periodo")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int periodo;

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TotpurchaseperpackandvalidityEntityPK that = (TotpurchaseperpackandvalidityEntityPK) o;

        if (packageId != that.packageId) return false;
        if (periodo != that.periodo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = packageId;
        result = 31 * result + periodo;
        return result;
    }
}
