package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ownoptservice", schema = "dbproj")
@IdClass(OwnoptserviceEntityPK.class)
public class OwnoptserviceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "refPack")
    private int refPack;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "refOptService")
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

        OwnoptserviceEntity that = (OwnoptserviceEntity) o;

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
