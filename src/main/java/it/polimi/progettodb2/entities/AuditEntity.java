package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "audit", schema = "dbproj", catalog = "")
@IdClass(AuditEntityPK.class)
public class AuditEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "refUser")
    private int refUser;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "refLastRejection")
    private int refLastRejection;

    @ManyToOne
    @JoinColumn(name = "refUser", referencedColumnName = "idUser", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "refLastRejection", referencedColumnName = "idPayments", nullable = false)
    private PaymentEntity paymentByRefLastRejection;

    public int getRefUser() {
        return refUser;
    }

    public void setRefUser(int refUser) {
        this.refUser = refUser;
    }

    public int getRefLastRejection() {
        return refLastRejection;
    }

    public void setRefLastRejection(int refLastRejection) {
        this.refLastRejection = refLastRejection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditEntity that = (AuditEntity) o;

        if (refUser != that.refUser) return false;
        if (refLastRejection != that.refLastRejection) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = refUser;
        result = 31 * result + refLastRejection;
        return result;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public PaymentEntity getPaymentByRefLastRejection() {
        return paymentByRefLastRejection;
    }

    public void setPaymentByRefLastRejection(PaymentEntity paymentByRefLastRejection) {
        this.paymentByRefLastRejection = paymentByRefLastRejection;
    }
}
