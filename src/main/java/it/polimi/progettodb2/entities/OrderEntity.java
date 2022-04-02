package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@NamedQuery(
        name = "Order.findByID",
        query = "SELECT o FROM OrderEntity o " +
                "WHERE o.idOrder = :idOrder"
)
@NamedQuery(
        name = "Order.findAllOrderByUser",
        query = "SELECT o FROM OrderEntity o " +
                "WHERE o.refUser = :user "
)
@NamedQuery(
        name = "Order.findRejectedOrdersOfUser",
        query = "SELECT o FROM OrderEntity o " +
                "WHERE o.refUser = :user AND " +
                "o.valid=false"
)
@NamedQuery(
        name = "Order.findAllOrder",
        query = "SELECT o FROM OrderEntity o "
)
@NamedQuery(
        name = "Order.findAllSuspendedOrder",
        query = "SELECT o FROM OrderEntity o "+
                "WHERE o.valid = false "
)


@Table(name = "order", schema = "dbproj")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrder")
    private int idOrder;
    
    @Column(name = "creationDate")
    private Timestamp creationDate;


    @Column(name = "paymentDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp paymentDate;
    
    @Column(name = "startDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Column(name = "periodo")
    private int periodo;
    
    @Column(name = "valid")
    private Boolean valid;
    
    @Column(name = "totalAmount")
    private float totalAmount;
    
    @Column(name = "nMobile")
    private String nMobile;
    
    @Column(name = "nFixed")
    private String nFixed;

    @ManyToOne(fetch=FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "refUser")
    private UserEntity refUser;

    @ManyToOne(fetch=FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(name = "refPack")
    private PackageEntity refPack;

    @ManyToMany
    @JoinTable(name="chosenoptional",
            joinColumns = @JoinColumn(name="refOrder"),
            inverseJoinColumns = @JoinColumn(name = "refOptService"))
    private List<OptserviceEntity> optService;

    @OneToOne(targetEntity = AuditEntity.class, mappedBy = "refOrder", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private AuditEntity audit;

    public OrderEntity(){}

    public OrderEntity(UserEntity refUser, PackageEntity refPack, Timestamp creationDate, Timestamp paymentDate, Date startDate, int period, Boolean valid, Float totalAmount, String nMobile, String nFixed, List<OptserviceEntity> optService){
            this.refUser=refUser;
            this.refPack=refPack;
            this.creationDate=creationDate;
            this.paymentDate=paymentDate;
            this.startDate=startDate;
            this.periodo=period;
            this.valid=valid;
            this.totalAmount=totalAmount;
            this.nMobile=nMobile;
            this.nFixed=nFixed;
            this.optService=optService;
    }


    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public UserEntity getRefUser() {
        return refUser;
    }

    public PackageEntity getRefPack() {
        return refPack;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getStartDate() {
        return startDate;
    }


    public int getPeriodo() {
        return periodo;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public String getnMobile() {
        return nMobile;
    }

    public String getnFixed() {
        return nFixed;
    }

    public List<OptserviceEntity> getOptServices(){return this.optService;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (idOrder != that.idOrder) return false;
        if (refUser != that.refUser) return false;
        if (refPack != that.refPack) return false;
        if (periodo != that.periodo) return false;
        if (totalAmount != that.totalAmount) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (paymentDate != null ? !paymentDate.equals(that.paymentDate) : that.paymentDate != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (valid != null ? !valid.equals(that.valid) : that.valid != null) return false;
        if (nMobile != null ? !nMobile.equals(that.nMobile) : that.nMobile != null) return false;
        return nFixed != null ? nFixed.equals(that.nFixed) : that.nFixed == null;
    }

    @Override
    public int hashCode() {
        int result = idOrder;
        result = 31 * result + (refUser != null ? refUser.hashCode() : 0);
        result = 31 * result + (refPack != null ? refPack.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + periodo;
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        result = 31 * result + (int) totalAmount;
        result = 31 * result + (nMobile != null ? nMobile.hashCode() : 0);
        result = 31 * result + (nFixed != null ? nFixed.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.refUser+" "+
        this.refPack+" "+
        this.creationDate+" "+
        this.paymentDate+" "+
        this.startDate+" "+
        this.periodo+" "+
        this.valid+" "+
        this.totalAmount+" "+
        this.nMobile+" "+
        this.nFixed+" "+
        this.optService;
    }
}
