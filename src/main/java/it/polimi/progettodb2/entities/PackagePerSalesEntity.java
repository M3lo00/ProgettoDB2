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

    @OneToOne(targetEntity = PackageEntity.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    )
    @JoinColumn(name = "package_id", referencedColumnName = "idPackage")
    private PackageEntity package_id;

    @Basic
    @Column(name = "justPack")
    private double justPack;
    @Basic
    @Column(name = "withOpt")
    private double withOpt;

    public double getJustPack() {
        return justPack;
    }

    public double getWithOpt() {
        return withOpt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackagePerSalesEntity that = (PackagePerSalesEntity) o;

        if (idSales != that.idSales) return false;
        if (package_id != that.package_id) return false;
        if (Double.compare(that.justPack, justPack) != 0) return false;
        return Double.compare(that.withOpt, withOpt) == 0;
    }






}
