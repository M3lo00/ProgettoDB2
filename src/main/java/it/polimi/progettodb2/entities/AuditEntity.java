package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity

@NamedQuery(
        name = "Audit.findAllAudit",
        query = "SELECT a FROM AuditEntity a "
)

@Table(name = "audit", schema = "dbproj")
public class AuditEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAudit")
    private int idAudit;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    )
    @JoinColumn(name = "refUser", referencedColumnName = "idUser")
    private UserEntity refUser;

    @OneToOne(targetEntity = OrderEntity.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    )
    @JoinColumn(name="refOrder", referencedColumnName = "idOrder")
    private OrderEntity refOrder;


    public int getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(int idAudit) {
        this.idAudit = idAudit;
    }

    public UserEntity getRefUser() {
        return refUser;
    }

    public void setRefUser(UserEntity refUser) {
        this.refUser = refUser;
    }

    public OrderEntity getRefOrder() {
        return refOrder;
    }

    public void setRefOrder(OrderEntity refOrder) {
        this.refOrder = refOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditEntity that = (AuditEntity) o;

        if (idAudit != that.idAudit) return false;
        if (refUser != that.refUser) return false;
        if (refOrder != that.refOrder) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAudit;
        result = 31 * result + (refUser != null ? refUser.hashCode() : 0);
        result = 31 * result + (refOrder != null ? refOrder.hashCode() : 0);
        return result;
    }
}
