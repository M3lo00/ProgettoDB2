package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity

//Seleziona tutte le tuple che hanno il packageid dato in ingresso
@NamedQuery(
        name = "TotalPurchasesPerPackage.findByPackage",
        query = "SELECT n FROM TotpurchaseperpackandvalidityEntity n " +
                "WHERE n.packageId = :package_id"
)




@Table(name = "totpurchaseperpackandvalidity", schema = "dbproj")
@IdClass(TotpurchaseperpackandvalidityEntityPK.class)
public class TotpurchaseperpackandvalidityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "package_id")
    private int packageId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "periodo")
    private int periodo;
    @Basic
    @Column(name = "totalPurchases")
    private int totalPurchases;

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
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

        if (packageId != that.packageId) return false;
        if (periodo != that.periodo) return false;
        if (totalPurchases != that.totalPurchases) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = packageId;
        result = 31 * result + periodo;
        result = 31 * result + totalPurchases;
        return result;
    }
}
