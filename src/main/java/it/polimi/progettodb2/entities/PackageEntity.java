package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "package", schema = "dbproj", catalog = "")
public class PackageEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPackage")
    private int idPackage;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "refEmployee")
    private Integer refEmployee;
    @Basic
    @Column(name = "sms")
    private Integer sms;
    @Basic
    @Column(name = "minute")
    private Integer minute;
    @Basic
    @Column(name = "mGiga")
    private Integer mGiga;
    @Basic
    @Column(name = "extraMGiga")
    private Double extraMGiga;
    @Basic
    @Column(name = "extraSMS")
    private Double extraSms;
    @Basic
    @Column(name = "fixedPhone")
    private Byte fixedPhone;
    @Basic
    @Column(name = "fGiga")
    private Integer fGiga;
    @Basic
    @Column(name = "extraFGiga")
    private Double extraFGiga;




    @OneToOne(mappedBy = "packageByRefPackage")
    private FeeperiodEntity feeperiodByIdPackage;


    @OneToMany(mappedBy = "refPack", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<OrderEntity> ordersPackage;


    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    ) @JoinColumn(name = "refEmployee", referencedColumnName = "idEmployee")
    private EmployeeEntity createdByEmployee;

    public int getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(Integer idPackage) {
        this.idPackage = idPackage;
    }

    public void setIdPackage(int idPackage) {
        this.idPackage = idPackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRefEmployee() {
        return refEmployee;
    }

    public void setRefEmployee(Integer refEmployee) {
        this.refEmployee = refEmployee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackageEntity that = (PackageEntity) o;

        if (idPackage != that.idPackage) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (refEmployee != null ? !refEmployee.equals(that.refEmployee) : that.refEmployee != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPackage;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (refEmployee != null ? refEmployee.hashCode() : 0);
        return result;
    }

    public Integer getSms() {
        return sms;
    }

    public void setSms(Integer sms) {
        this.sms = sms;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getmGiga() {
        return mGiga;
    }

    public void setmGiga(Integer mGiga) {
        this.mGiga = mGiga;
    }

    public Double getExtraMGiga() {
        return extraMGiga;
    }

    public void setExtraMGiga(Double extraMGiga) {
        this.extraMGiga = extraMGiga;
    }

    public Double getExtraSms() {
        return extraSms;
    }

    public void setExtraSms(Double extraSms) {
        this.extraSms = extraSms;
    }

    public Byte getFixedPhone() {
        return fixedPhone;
    }

    public void setFixedPhone(Byte fixedPhone) {
        this.fixedPhone = fixedPhone;
    }

    public Integer getfGiga() {
        return fGiga;
    }

    public void setfGiga(Integer fGiga) {
        this.fGiga = fGiga;
    }

    public Double getExtraFGiga() {
        return extraFGiga;
    }

    public void setExtraFGiga(Double extraFGiga) {
        this.extraFGiga = extraFGiga;
    }

    public FeeperiodEntity getFeeperiodByIdPackage() {
        return feeperiodByIdPackage;
    }

    public void setFeeperiodByIdPackage(FeeperiodEntity feeperiodByIdPackage) {
        this.feeperiodByIdPackage = feeperiodByIdPackage;
    }

    public Collection<OrderEntity> getOrdersByIdPackage() {
        return ordersPackage;
    }

    public void setOrdersByIdPackage(Collection<OrderEntity> ordersByIdPackage) {
        this.ordersPackage = ordersByIdPackage;
    }

    public EmployeeEntity getEmployeeByRefEmployee() {
        return createdByEmployee;
    }

    public void setEmployeeByRefEmployee(EmployeeEntity employeeByRefEmployee) {
        this.createdByEmployee = employeeByRefEmployee;
    }
}
