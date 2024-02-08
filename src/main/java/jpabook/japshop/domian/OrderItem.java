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


    @JoinColumn(name = "ITEM_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    @JoinColumn(name="ORDER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    private int orderPrice;
    private int count;


    //    생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);


        item.removeStock(count);
        return orderItem;

    }

//    비즈니스 로직
    public void cancel() {
        getItem().addStock(count);

    }

//    조회 로직

//    주문 상품 전제 가격 조회
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
