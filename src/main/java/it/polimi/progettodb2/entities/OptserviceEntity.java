package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "optservice", schema = "dbproj")
public class OptserviceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idOptService")
    private int idOptService;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "monthly")
    private int monthly;
    
    @Column(name = "refEmployee")
    private Integer refEmployee;

    public int getIdOptService() {
        return idOptService;
    }

    public void setIdOptService(int idOptService) {
        this.idOptService = idOptService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonthly() {
        return monthly;
    }

    public void setMonthly(int monthly) {
        this.monthly = monthly;
    }

    public Integer getRefEmployee() {
        return refEmployee;
    }

    public void setRefEmployee(Integer refEmployee) {
        this.refEmployee = refEmployee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptserviceEntity that = (OptserviceEntity) o;

        if (idOptService != that.idOptService) return false;
        if (monthly != that.monthly) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (refEmployee != null ? !refEmployee.equals(that.refEmployee) : that.refEmployee != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOptService;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + monthly;
        result = 31 * result + (refEmployee != null ? refEmployee.hashCode() : 0);
        return result;
    }
}
