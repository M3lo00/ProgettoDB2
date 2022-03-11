package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "audit", schema = "dbproj")
public class AuditEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAudit")
    private int idAudit;
    
    @Column(name = "refUser")
    private int refUser;
    
    @Column(name = "refLastRejection")
    private int refLastRejection;

    public int getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(int idAudit) {
        this.idAudit = idAudit;
    }

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

        if (idAudit != that.idAudit) return false;
        if (refUser != that.refUser) return false;
        if (refLastRejection != that.refLastRejection) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAudit;
        result = 31 * result + refUser;
        result = 31 * result + refLastRejection;
        return result;
    }
}
