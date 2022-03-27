package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;
import java.sql.Date;
import java.sql.Timestamp;


@Entity

@NamedQuery(
        name = "Package.findAll",
        query = "SELECT u " +
                "FROM PackageEntity u "
)

@NamedQuery(
        name = "ServicePackageToSelect.findAll",
        query = "SELECT stp " +
                "FROM PackageEntity stp "
)

@NamedQuery(
        name = "Service.findAll",
        query = "SELECT o FROM PackageEntity o "
)


@Table(name = "package", schema = "dbproj")
public class PackageEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPackage")
    private int idPackage;

    @Column(name = "name")
    private String name;

    @Column(name = "sms")
    private Integer sms;

    @Column(name = "minute")
    private Integer minute;

    @Column(name = "mGiga")
    private Integer mGiga;

    @Column(name = "extraMGiga")
    private Float extraMGiga;

    @Column(name = "extraSMS")
    private Float extraSms;

    @Column(name = "fixedPhone")
    private Byte fixedPhone;

    @Column(name = "fGiga")
    private Integer fGiga;

    @Column(name = "extraFGiga")
    private Float extraFGiga;

    @Column(name = "price12M")
    private double price12M;

    @ManyToOne(fetch=FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "refEmployee")
    private EmployeeEntity refEmployee;

    @OneToMany(targetEntity = OrderEntity.class, mappedBy = "refPack", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<OrderEntity> orders;

    @OneToMany(targetEntity = TotpurchaseperpackandvalidityEntity.class, mappedBy = "package_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TotpurchaseperpackandvalidityEntity> totpurch;


    @OneToOne(targetEntity = PackagePerSalesEntity.class, fetch = FetchType.EAGER, mappedBy="package_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private PackagePerSalesEntity packagesales;


    @OneToOne(targetEntity = AvgproductperserviceEntity.class, fetch = FetchType.EAGER, mappedBy="package_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private AvgproductperserviceEntity avgproduct;




    @ManyToMany
    @JoinTable(name="ownoptservice",
            joinColumns = @JoinColumn(name="refPack"),
            inverseJoinColumns = @JoinColumn(name = "refOptService"))
    private List<OptserviceEntity> optService;

    public List<OptserviceEntity> getOptService(){return optService;}

    public void setOptService(List<OptserviceEntity> opts){this.optService=opts;}

    public PackageEntity() {
    }

    public PackageEntity(String name, Integer sms, Integer minute, Integer mGiga, Float extraMGiga, Float extraSms, Byte fixedPhone, Integer fGiga, Float extraFGiga, Float price12M) {
        this.name=name;
        this.sms=sms;
        this.minute=minute;
        this.mGiga=mGiga;
        this.extraMGiga=extraMGiga;
        this.extraSms=extraSms;
        this.fixedPhone=fixedPhone;
        this.fGiga=fGiga;
        this.extraFGiga=extraFGiga;
        this.price12M=price12M;
    }

    public List<TotpurchaseperpackandvalidityEntity> getTotpurch(){return totpurch;}


    public AvgproductperserviceEntity getAvgproduct(){ return avgproduct;}

    public PackagePerSalesEntity getPackageSales(){ return packagesales;}





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

    public EmployeeEntity getRefEmployee() {
        return refEmployee;
    }

    public void setRefEmployee(EmployeeEntity refEmployee) {
        this.refEmployee = refEmployee;
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

    public Float getExtraMGiga() {
        return extraMGiga;
    }


    public void setExtraMGiga(Float extraMGiga) {
        this.extraMGiga = extraMGiga;
    }

    public Float getExtraSms() {
        return extraSms;
    }


    public void setExtraSms(Float extraSms) {
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

    public Float getExtraFGiga() {
        return extraFGiga;
    }


    public void setExtraFGiga(Float extraFGiga) {
        this.extraFGiga = extraFGiga;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackageEntity that = (PackageEntity) o;

        if (idPackage != that.idPackage) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (refEmployee != null ? !refEmployee.equals(that.refEmployee) : that.refEmployee != null) return false;
        if (sms != null ? !sms.equals(that.sms) : that.sms != null) return false;
        if (minute != null ? !minute.equals(that.minute) : that.minute != null) return false;
        if (mGiga != null ? !mGiga.equals(that.mGiga) : that.mGiga != null) return false;
        if (extraMGiga != null ? !extraMGiga.equals(that.extraMGiga) : that.extraMGiga != null) return false;
        if (extraSms != null ? !extraSms.equals(that.extraSms) : that.extraSms != null) return false;
        if (fixedPhone != null ? !fixedPhone.equals(that.fixedPhone) : that.fixedPhone != null) return false;
        if (fGiga != null ? !fGiga.equals(that.fGiga) : that.fGiga != null) return false;
        if (extraFGiga != null ? !extraFGiga.equals(that.extraFGiga) : that.extraFGiga != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPackage;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (refEmployee != null ? refEmployee.hashCode() : 0);
        result = 31 * result + (sms != null ? sms.hashCode() : 0);
        result = 31 * result + (minute != null ? minute.hashCode() : 0);
        result = 31 * result + (mGiga != null ? mGiga.hashCode() : 0);
        result = 31 * result + (extraMGiga != null ? extraMGiga.hashCode() : 0);
        result = 31 * result + (extraSms != null ? extraSms.hashCode() : 0);
        result = 31 * result + (fixedPhone != null ? fixedPhone.hashCode() : 0);
        result = 31 * result + (fGiga != null ? fGiga.hashCode() : 0);
        result = 31 * result + (extraFGiga != null ? extraFGiga.hashCode() : 0);
        return result;
    }

    public double getPrice12M() {
        return price12M;
    }

    public void setPrice12M(double price12M) {
        this.price12M = price12M;
    }
}
