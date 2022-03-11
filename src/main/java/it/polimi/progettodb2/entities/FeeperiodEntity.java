package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "feeperiod", schema = "dbproj")
public class FeeperiodEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idFeePeriod")
    private int idFeePeriod;
    
    @Column(name = "refPackage")
    private int refPackage;
    
    @Column(name = "periodo")
    private int periodo;
    
    @Column(name = "fee")
    private Integer fee;

    public int getIdFeePeriod() {
        return idFeePeriod;
    }

    public void setIdFeePeriod(int idFeePeriod) {
        this.idFeePeriod = idFeePeriod;
    }

    public int getRefPackage() {
        return refPackage;
    }

    public void setRefPackage(int refPackage) {
        this.refPackage = refPackage;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
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

        if (idFeePeriod != that.idFeePeriod) return false;
        if (refPackage != that.refPackage) return false;
        if (periodo != that.periodo) return false;
        if (fee != null ? !fee.equals(that.fee) : that.fee != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFeePeriod;
        result = 31 * result + refPackage;
        result = 31 * result + periodo;
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        return result;
    }
}
