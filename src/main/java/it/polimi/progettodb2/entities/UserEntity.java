package it.polimi.progettodb2.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "dbproj")

@NamedQuery(name = "User.checkCredentials",
        query = "SELECT r FROM UserEntity r  WHERE r.username = ?1 and r.password = ?2")


@NamedQuery(
        name = "User.findByUsername",
        query = "SELECT u " +
                "FROM UserEntity u " +
                "WHERE u.username = :username"
)

@NamedQuery(
        name = "User.findByEmail",
        query = "SELECT u " +
                "FROM UserEntity u " +
                "WHERE u.email = :email"
)

@NamedQuery(
        name = "User.findByID",
        query = "SELECT u " +
                "FROM UserEntity u " +
                "WHERE u.idUser = : idUser"
)

public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUser")
    private int idUser;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "Username")
    private String username;
    
    @Column(name = "Password")
    private String password;
    
    @Column(name = "Insolvent")
    private Boolean insolvent;
    
    @Column(name = "failedPay")
    private Integer failedPay;

    @OneToMany(targetEntity = OrderEntity.class, fetch = FetchType.EAGER, mappedBy="refUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<OrderEntity> orders;

    @OneToMany(targetEntity = PaymentEntity.class, fetch = FetchType.LAZY, mappedBy="refUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<PaymentEntity> pay;

    @OneToOne(targetEntity = AuditEntity.class, fetch = FetchType.EAGER, mappedBy="refUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrderEntity audits;

    public UserEntity() {
    }

    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        failedPay=0;
        insolvent=false;
    }
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getInsolvent() {
        return insolvent;
    }

    public void setInsolvent(Boolean insolvent) {
        this.insolvent = insolvent;
    }

    public Integer getFailedPay() {
        return failedPay;
    }

    public void setFailedPay(Integer failedPay) {
        this.failedPay = failedPay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (idUser != that.idUser) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (insolvent != null ? !insolvent.equals(that.insolvent) : that.insolvent != null) return false;
        if (failedPay != null ? !failedPay.equals(that.failedPay) : that.failedPay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (insolvent != null ? insolvent.hashCode() : 0);
        result = 31 * result + (failedPay != null ? failedPay.hashCode() : 0);
        return result;
    }
}
