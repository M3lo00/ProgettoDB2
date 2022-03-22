package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "ownoptservice", schema = "dbproj")
@IdClass(OwnoptserviceEntityPK.class)
public class OwnoptserviceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch=FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @Id
    @JoinColumn(name = "refPack")
    private PackageEntity refPack;

    @ManyToOne(fetch=FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @Id
    @JoinColumn(name = "refOptService")
    private OptserviceEntity refOptService;

    public PackageEntity getRefPack() {
        return refPack;
    }

    public void setRefPack(PackageEntity refPack) {
        this.refPack = refPack;
    }

    public OptserviceEntity getRefOptService() {
        return refOptService;
    }

    public void setRefOptService(OptserviceEntity refOptService) {
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

}
