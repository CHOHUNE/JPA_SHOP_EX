package jpabook.japshop.domain;


import jakarta.persistence.*;
import jpabook.japshop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED )
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;


    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;
    private int orderPrice;
    private int count;

    public void add(OrderItem orderItems) {
    }

//    비즈니스 로직
    public void cancel() {

        getItem().addStock(count);
    }

//    조회 로직
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

//    생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        orderItem.setItem(item);

        item.removeStock(count);
        return orderItem;
    }


}
