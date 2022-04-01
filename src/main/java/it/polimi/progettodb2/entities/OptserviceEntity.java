package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity

@NamedQuery(
        name="Optional.findAllOptionalProduct",
        query = "SELECT o FROM OptserviceEntity o "
)

@Table(name = "optservice", schema = "dbproj")
public class OptserviceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idOptService")
    private int idOptService;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "monthly")
    private double monthly;

    @Basic
    @Column(name = "totSold")
    private Float totSold;

    @ManyToOne(fetch=FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "refEmployee")
    private EmployeeEntity refEmployee;

    public OptserviceEntity(){}

    public OptserviceEntity(EmployeeEntity emp, String name, Float price){
        this.refEmployee=emp;
        this.name=name;
        this.monthly=price;
        this.totSold= (float) 0;
    }

    public int getIdOptService() {
        return idOptService;
    }

    public void setIdOptService(Integer idOptService) {
        this.idOptService = idOptService;
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

    public double getMonthly() {
        return monthly;
    }

    public void setMonthly(double monthly) {
        this.monthly = monthly;
    }

    public EmployeeEntity getRefEmployee() {
        return refEmployee;
    }

    public void setRefEmployee(EmployeeEntity refEmployee) {
        this.refEmployee = refEmployee;
    }

    public Float getTotSold(){return this.totSold;}



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
        result = 31 * result + (int)monthly;
        result = 31 * result + (refEmployee != null ? refEmployee.hashCode() : 0);
        return result;
    }
}
