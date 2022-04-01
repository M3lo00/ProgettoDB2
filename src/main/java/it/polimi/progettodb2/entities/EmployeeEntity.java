package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "employee", schema = "dbproj")

@NamedQueries(
        {
                @NamedQuery(
                        name = "Employee.findByUsername",
                        query = "SELECT e " +
                                "FROM EmployeeEntity e " +
                                "WHERE e.username = :username"
                ),

                @NamedQuery(
                        name = "Employee.findByEmail",
                        query = "SELECT e FROM EmployeeEntity e " +
                                "WHERE e.email = :email"
                ),
                @NamedQuery(
                        name = "Employee.checkCredentials",
                        query = "SELECT e FROM EmployeeEntity " +
                                "e  WHERE e.username = ?1 and e.password = ?2"
                )
        }
)

public class EmployeeEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idEmployee")
    private int idEmployee;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;
    

    @OneToMany(targetEntity = PackageEntity.class, mappedBy = "refEmployee", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}, orphanRemoval = true)
    private Collection<PackageEntity> packagesRelatedToEmployee;

    @OneToMany(targetEntity = OptserviceEntity.class, mappedBy = "refEmployee", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}, orphanRemoval = true)
    private Collection<OptserviceEntity> optserviceRelatedToEmployee;

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (idEmployee != that.idEmployee) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEmployee;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
