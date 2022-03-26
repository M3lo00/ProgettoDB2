package it.polimi.progettodb2.entities;
import jakarta.persistence.*;

@Entity

@NamedQuery(
        name = "PackagePerSales.findAllSales",
        query = "SELECT s FROM PackagePerSalesEntity s "
)

@Table(name = "packagesales", schema = "dbproj")
public class PackagePerSalesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSales")
    private int idSales;

    @ManyToOne(fetch=FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "package_id")
    private PackageEntity package_id;

    @Basic
    @Column(name = "justPack")
    private double justPack;
    @Basic
    @Column(name = "withOpt")
    private double withOpt;

    public int getIdSales() {
        return idSales;
    }
    public void setIdSales(int idAvgProduct) {
        this.idSales = idSales;
    }

    public PackageEntity getPackageId() {
        return package_id;
    }
    public void setPackageId(PackageEntity packageId) {
        this.package_id = packageId;
    }

    public double getJustPack() {
        return justPack;
    }
    public void setJustPack(double justPack) {
        this.justPack = justPack;
    }

    public double getWithOpt() {
        return withOpt;
    }
    public void setWithOpt(double withOpt) {
        this.withOpt = withOpt;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackagePerSalesEntity that = (PackagePerSalesEntity) o;

        if (idSales != that.idSales) return false;
        if (package_id != that.package_id) return false;
        if (Double.compare(that.justPack, justPack) != 0) return false;
        if (Double.compare(that.withOpt, withOpt) != 0) return false;


        return true;
    }






}
