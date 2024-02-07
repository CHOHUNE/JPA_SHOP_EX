package jpabook.japshop.domian;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    @JoinColumn(name = "ORDER_ID")
    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus; //READY, COMP

}
