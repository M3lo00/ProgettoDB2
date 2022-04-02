package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity

@NamedQuery(
        name = "TotalPurchasesPerPackage.findAllTot",
        query = "SELECT n FROM TotpurchaseperpackandvalidityEntity n "
)

@Table(name = "totpurchaseperpackandvalidity", schema = "dbproj")

public class TotpurchaseperpackandvalidityEntity implements Serializable{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column (name = "idTotalPurchase")
    private int idTotalPurchase;

    @ManyToOne(fetch=FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "package_id")
    private PackageEntity package_id;

    @Column(name = "periodo")
    private int periodo;

    @Basic
    @Column(name = "totalPurchases")
    private int totalPurchases;

    public int getTotalPurchases() {
        return totalPurchases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TotpurchaseperpackandvalidityEntity that = (TotpurchaseperpackandvalidityEntity) o;

        if (idTotalPurchase != that.idTotalPurchase) return false;
        if (package_id != that.package_id) return false;
        if (periodo != that.periodo) return false;
        return totalPurchases == that.totalPurchases;
    }
}
