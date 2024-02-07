package jpabook.japshop.domian;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

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
}
