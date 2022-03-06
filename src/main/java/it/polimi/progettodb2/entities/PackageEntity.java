package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "package", schema = "dbproj")
public class PackageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPackage")
    private int idPackage;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "refEmployee")
    private Integer refEmployee;

    public int getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(int idPackage) {
        this.idPackage = idPackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        PackageEntity that = (PackageEntity) o;

        if (idPackage != that.idPackage) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (refEmployee != null ? !refEmployee.equals(that.refEmployee) : that.refEmployee != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPackage;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (refEmployee != null ? refEmployee.hashCode() : 0);
        return result;
    }
}
