package it.polimi.progettodb2.entities;
import jakarta.persistence.*;

import java.util.List;


@Entity

@NamedQuery(
        name = "InsolventUser.findAllInsolvent",
        query = "SELECT i FROM InsolventUserEntity i "
)
@Table(name = "insolventuser", schema = "dbproj")
public class InsolventUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInsolventUser")
    private int idInsolventUser;



    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    )
    @JoinColumn(name = "insolvent_id", referencedColumnName = "idUser")
    private UserEntity insolvent_id;



    public int getIdInsolventUser() {
        return idInsolventUser;
    }

    public void setIdInsolventUser(int idInsolventUser) {
        this.idInsolventUser = idInsolventUser;
    }

    public UserEntity getInsolventId() {
        return insolvent_id;
    }

    public void setInsolventId(UserEntity packageId) {
        this.insolvent_id = insolvent_id;
    }



}
