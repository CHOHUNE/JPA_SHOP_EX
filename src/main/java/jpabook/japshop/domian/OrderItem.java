package jpabook.japshop.domian;


import jakarta.persistence.*;
import jpabook.japshop.domian.Item.Item;
import lombok.Data;

@Entity
@Data
public class OrderItem {

    @Id
    @Column(name="ORDER_ITEM_ID")
    @GeneratedValue
    private Long id;

    @JoinColumn(name="ORDER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @JoinColumn(name = "ITEM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    private int orderPrice;
    private int count;
}
