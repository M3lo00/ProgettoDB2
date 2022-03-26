package it.polimi.progettodb2.entities;
import jakarta.persistence.*;

@Entity

@NamedQuery(
        name = "SuspendedOrder.findAllSuspendedOrder",
        query = "SELECT s FROM SuspendedOrderEntity s "
)


@Table(name = "susporder", schema = "dbproj")

public class SuspendedOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSuspended")
    private int idSuspended;



    @OneToOne(targetEntity = OrderEntity.class, fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}
    )
    @JoinColumn(name = "order_id", referencedColumnName = "idOrder")
    private OrderEntity order_id;

    public int getIdSuspended() {
        return idSuspended;
    }

    public void setIdSuspended(int idSuspended) {
        this.idSuspended = idSuspended;
    }

    public OrderEntity getOrder_id() {
        return order_id;
    }

    public void setOrder_id(OrderEntity packageId) {
        this.order_id = order_id;
    }


}
