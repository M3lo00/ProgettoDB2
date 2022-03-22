package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "avgproductperservice", schema = "dbproj")
public class AvgproductperserviceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "package_id")
    private int packageId;
    @Basic
    @Column(name = "avgnumber")
    private double avgnumber;
    @Basic
    @Column(name = "numoptservice")
    private int numoptservice;
    @Basic
    @Column(name = "numpackage")
    private int numpackage;

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
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

        if (packageId != that.packageId) return false;
        if (Double.compare(that.avgnumber, avgnumber) != 0) return false;
        if (numoptservice != that.numoptservice) return false;
        if (numpackage != that.numpackage) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = packageId;
        temp = Double.doubleToLongBits(avgnumber);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + numoptservice;
        result = 31 * result + numpackage;
        return result;
    }
}
