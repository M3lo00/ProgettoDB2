package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity


@NamedQuery(
        name = "AvgProductPerService.findAllAvg",
        query = "SELECT a FROM AvgproductperserviceEntity a "
)


@Table(name = "avgproductperservice", schema = "dbproj")
public class AvgproductperserviceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAvgProduct")
    private int idAvgProduct;


    @OneToOne(targetEntity = PackageEntity.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    )
    @JoinColumn(name = "package_id", referencedColumnName = "idPackage")
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

    public double getAvgnumber() {
        return avgnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvgproductperserviceEntity that = (AvgproductperserviceEntity) o;

        if (idAvgProduct != that.idAvgProduct) return false;
        if (Double.compare(that.avgnumber, avgnumber) != 0) return false;
        if (numoptservice != that.numoptservice) return false;
        if (numpackage != that.numpackage) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = 0;
        temp = Double.doubleToLongBits(avgnumber);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + numoptservice;
        result = 31 * result + numpackage;
        return result;
    }
}
