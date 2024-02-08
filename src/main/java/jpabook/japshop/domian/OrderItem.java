package jpabook.japshop.domian;


import jakarta.persistence.*;
import jpabook.japshop.domian.Item.Item;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

//    protected OrderItem() {
//      해당 생성자 옵션은  롬복 어노테이션으로도 줄수 있다.
//    @NoArgsConstructor(access = AccessLevel.PROTECTED)
//    }


    //사람들과 협업시에 protected 를 걸어 생성자 사용을 제한하고 싶을 때 : protdcted를 쓰면 해당 클래스와 하위 클래스만 사용할 수 있다.
//    JPA 에서는 protected 까지만 지원한다.  외부에서 인스턴스를 생성할 수 없으므로, 외부에서 클래스의 생성을 제한할 수 있습니다.
//    이는 클래스를 더 강력하게 캡슐화하고, 의도하지 않은 객체 생성을 방지하는 데 도움이 된다.

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
