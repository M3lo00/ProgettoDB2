package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity

//Seleziona tutte le tuple che hanno il packageid dato in ingresso
@NamedQuery(
        name = "TotalPurchasesPerPackage.findAllTot",
        query = "SELECT n FROM TotpurchaseperpackandvalidityEntity n " //+
                //"WHERE n.packageId = :package_id"
)








@Table(name = "totpurchaseperpackandvalidity", schema = "dbproj")

public class TotpurchaseperpackandvalidityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public int getIdTotalPurchase() {
        return idTotalPurchase;
    }

    public void setIdTotalPurchase(Integer idTotalPurchase) {
        this.idTotalPurchase = idTotalPurchase;
    }

    public PackageEntity getPackageId() {
        return package_id;
    }

    public void setPackageId(PackageEntity packageId) {
        this.package_id = packageId;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public int getTotalPurchases() {
        return totalPurchases;
    }

    public void setTotalPurchases(int totalPurchases) {
        this.totalPurchases = totalPurchases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TotpurchaseperpackandvalidityEntity that = (TotpurchaseperpackandvalidityEntity) o;

        if (idTotalPurchase != that.idTotalPurchase) return false;
        if (package_id != that.package_id) return false;
        if (periodo != that.periodo) return false;
        if (totalPurchases != that.totalPurchases) return false;

        return true;
    }

    /*
    @Override
    public int hashCode() {
        int result = this.package_id;
        result = 31 * result + periodo;
        result = 31 * result + totalPurchases;
        return result;
    }

     */
}
