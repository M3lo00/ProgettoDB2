package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ownoptservice", schema = "dbproj")
@IdClass(OwnoptserviceEntityPK.class)
public class OwnoptserviceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "refServPack")
    private int refServPack;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "refOptService")
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

        OwnoptserviceEntity that = (OwnoptserviceEntity) o;

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
