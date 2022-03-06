package it.polimi.progettodb2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "service", schema = "dbproj")
public class ServiceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idService")
    private int idService;
    @Basic
    @Column(name = "Type")
    private String type;

    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceEntity that = (ServiceEntity) o;

        if (idService != that.idService) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idService;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
