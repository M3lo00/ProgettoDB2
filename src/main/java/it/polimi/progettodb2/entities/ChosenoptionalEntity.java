package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "chosenoptional", schema = "dbproj")
@IdClass(ChosenoptionalEntityPK.class)
public class ChosenoptionalEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "refOrder")
    private int refOrder;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "refOptService")
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

        ChosenoptionalEntity that = (ChosenoptionalEntity) o;

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
