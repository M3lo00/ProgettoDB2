package it.polimi.progettodb2.entities;
import jakarta.persistence.*;


@Entity
@NamedQuery(
        name = "BestOptional.findAllBestOptional",
        query = "SELECT b FROM BestOptionalEntity b "+
                "ORDER BY b.totalValue desc"
)

@Table(name = "bestOpt", schema = "dbproj")
public class BestOptionalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bestOpt_id")
    private int bestOpt_id;

    @OneToOne(targetEntity = OptserviceEntity.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    )
    @JoinColumn(name = "opt_id", referencedColumnName = "idOptService")
    private OptserviceEntity opt_id;

    @Basic
    @Column(name = "totalValue")
    private int totalValue;

    public int getBestOpt_id() {
        return bestOpt_id;
    }

    public void setBestOpt_id(int bestOpt_id) {
        this.bestOpt_id = bestOpt_id;
    }

    public OptserviceEntity getOpt_id() {return opt_id;}

    public void setOpt_id(OptserviceEntity opt_id) {this.opt_id = opt_id;}

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }



}
