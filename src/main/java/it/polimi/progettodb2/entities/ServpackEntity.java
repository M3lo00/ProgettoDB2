package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "servpack", schema = "dbproj")
@IdClass(ServpackEntityPK.class)
public class ServpackEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "refService")
    private int refService;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "refPackage")
    private int refPackage;
    @Basic
    @Column(name = "sms")
    private Integer sms;
    @Basic
    @Column(name = "minute")
    private Integer minute;
    @Basic
    @Column(name = "giga")
    private Integer giga;
    @Basic
    @Column(name = "extraGigaFee")
    private Integer extraGigaFee;
    @Basic
    @Column(name = "ServPackcol")
    private Double servPackcol;

    public int getRefService() {
        return refService;
    }

    public void setRefService(int refService) {
        this.refService = refService;
    }

    public int getRefPackage() {
        return refPackage;
    }

    public void setRefPackage(int refPackage) {
        this.refPackage = refPackage;
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

    public Integer getGiga() {
        return giga;
    }

    public void setGiga(Integer giga) {
        this.giga = giga;
    }

    public Integer getExtraGigaFee() {
        return extraGigaFee;
    }

    public void setExtraGigaFee(Integer extraGigaFee) {
        this.extraGigaFee = extraGigaFee;
    }

    public Double getServPackcol() {
        return servPackcol;
    }

    public void setServPackcol(Double servPackcol) {
        this.servPackcol = servPackcol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServpackEntity that = (ServpackEntity) o;

        if (refService != that.refService) return false;
        if (refPackage != that.refPackage) return false;
        if (sms != null ? !sms.equals(that.sms) : that.sms != null) return false;
        if (minute != null ? !minute.equals(that.minute) : that.minute != null) return false;
        if (giga != null ? !giga.equals(that.giga) : that.giga != null) return false;
        if (extraGigaFee != null ? !extraGigaFee.equals(that.extraGigaFee) : that.extraGigaFee != null) return false;
        if (servPackcol != null ? !servPackcol.equals(that.servPackcol) : that.servPackcol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refService;
        result = 31 * result + refPackage;
        result = 31 * result + (sms != null ? sms.hashCode() : 0);
        result = 31 * result + (minute != null ? minute.hashCode() : 0);
        result = 31 * result + (giga != null ? giga.hashCode() : 0);
        result = 31 * result + (extraGigaFee != null ? extraGigaFee.hashCode() : 0);
        result = 31 * result + (servPackcol != null ? servPackcol.hashCode() : 0);
        return result;
    }
}
