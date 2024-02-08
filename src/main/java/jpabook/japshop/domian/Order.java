package jpabook.japshop.domian;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="orders") //관례 중 하나로 복수형으로 표현되는 entity는 해당 테이ㅡㅂㄹ에 여러 레코드가 포함되어 있음을 나타낸다.
public class Order {


    @GeneratedValue
    @Id
    @Column(name="ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="DELIVERY_ID")
    private Delivery delivery;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
//    @JoinColumn(name = "ORDER_ITEM_ID")
    private List<OrderItem> orderItems = new ArrayList<>(); //빈 배열을 넣어야 한다. 변수명은 s를 붙여서 복수형

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //    연관관계 편의 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);

        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);

    }

    //    생성 메서드
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        // 여기서 쓰여진 ...는 가변인자로 OrderItem의 갯수를 동적으로 조절할 수 있다.
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);

        for (OrderItem orderItem : orderItems) {

            order.addOrderItem(orderItem);

        }
        order.setOrderStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;

    }
//    비즈니스 로직
//    주문 취소- 상태를 변경하고 재고를 올려야 하는데 배송 전이여야 함 -> 배송 후면 예외 발생
    public void cancel(){
        if (delivery.getDeliveryStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("이미 배송이 완료된 상품은 취소가 불가 합니다.");

        }
        this.setOrderStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel(); // Order 뿐만 아니라 여러개의 orderItem 에도 캔슬을 각각 해줘야 함
            // 취소된 아이템의 재고를 올려주는 메서드

        }
    }

    //조회 목적
    public int getTotalPrice(){
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;

    }

}
