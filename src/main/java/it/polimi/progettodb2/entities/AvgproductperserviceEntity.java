package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity


@NamedQuery(
        name = "AvgProductPerService.findAllAvg",
        query = "SELECT a FROM AvgproductperserviceEntity a "
)


@Table(name = "avgproductperservice", schema = "dbproj")
public class AvgproductperserviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAvgProduct")
    private int idAvgProduct;

    @ManyToOne(fetch=FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "package_id")
    private PackageEntity package_id;

    @Basic
    @Column(name = "avgnumber")
    private double avgnumber;
    @Basic
    @Column(name = "numoptservice")
    private int numoptservice;
    @Basic
    @Column(name = "numpackage")
    private int numpackage;

    public int getIdAvgProduct() {
        return idAvgProduct;
    }

    public void setIdAvgProduct(int idAvgProduct) {
        this.idAvgProduct = idAvgProduct;
    }

    public PackageEntity getPackageId() {
        return package_id;
    }

    public void setPackageId(PackageEntity packageId) {
        this.package_id = packageId;
    }

    public double getAvgnumber() {
        return avgnumber;
    }

    public void setAvgnumber(double avgnumber) {
        this.avgnumber = avgnumber;
    }

    public int getNumoptservice() {
        return numoptservice;
    }

    public void setNumoptservice(int numoptservice) {
        this.numoptservice = numoptservice;
    }

    public int getNumpackage() {
        return numpackage;
    }

    public void setNumpackage(int numpackage) {
        this.numpackage = numpackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvgproductperserviceEntity that = (AvgproductperserviceEntity) o;

        if (idAvgProduct != that.idAvgProduct) return false;
        //if (packageId != that.packageId) return false;
        if (Double.compare(that.avgnumber, avgnumber) != 0) return false;
        if (numoptservice != that.numoptservice) return false;
        if (numpackage != that.numpackage) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        //result = packageId;
        result = 0;
        temp = Double.doubleToLongBits(avgnumber);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + numoptservice;
        result = 31 * result + numpackage;
        return result;
    }
}
