package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ownoptservice", schema = "dbproj", catalog = "")
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
    @ManyToOne
    @JoinColumn(name = "refPack", referencedColumnName = "idPackage", nullable = false)
    private PackageEntity optServPackage;
    @ManyToOne
    @JoinColumn(name = "refOptService", referencedColumnName = "idOptService", nullable = false)
    private OptserviceEntity optserviceByRefOptService;

    public int getRefServPack() {
        return refPack;
    }

    public void setRefServPack(int refPack) {
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

    public PackageEntity getOptServPackage() {
        return optServPackage;
    }

    public void setPackageByRefServPack(PackageEntity optServPackage) {
        this.optServPackage = optServPackage;
    }

    public OptserviceEntity getOptserviceByRefOptService() {
        return optserviceByRefOptService;
    }

    public void setOptserviceByRefOptService(OptserviceEntity optserviceByRefOptService) {
        this.optserviceByRefOptService = optserviceByRefOptService;
    }
}
