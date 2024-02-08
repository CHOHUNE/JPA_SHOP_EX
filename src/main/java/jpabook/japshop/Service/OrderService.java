package jpabook.japshop.Service;


import jpabook.japshop.domian.*;
import jpabook.japshop.domian.Item.Item;
import jpabook.japshop.repository.ItemRepository;
import jpabook.japshop.repository.MemberRepository;
import jpabook.japshop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setDeliveryStatus(DeliveryStatus.READY);


//        주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

//        주문 생성
        Order order = Order.createOrder(member, delivery ,orderItem);

        orderRepository.save(order);
//        오더 아이템과 딜리버리는 따로 레포지토리에 save를 안하는 이유 -> 캐스케이드 옵션 때문.
//        order save 시(persist) 연관된 Delivery 와 OrderItemd은 cascadeType.ALL 로 인하여 전부 자동으로 생성된다.
//        어디까지 캐스케이드를 해야할까? 명확하게 딱잘라서 말하긴 애매하지만 주인관계가 서로 해당 엔티티만 참조하는 경우에만 사용해야한다
//        가령 딜리버리는 오더만 참조하고, 오더는 딜리버리만 참조하는 상황에서만 쓴다. 즉 라이프사이클이 동일하게 관리되어질 때. 프라이빗 오너일 때.
//        반대로 딜리버리가 매우 중요해서 여기 저기 참조되는 경우라면 복잡하게 얽혀들어가 예상치 못한 결과를 초래할 수 있으므로 사용을 지양하고 별도로
//        persist 해줘야 한다.
        return order.getId();
    }
//    주문 생선 메서드는 데이터를 변경하기 때문에 Transactional이 필요하고 주문한 멤버 아이디, 아이템 아이디, 수량이 필요하다
//    주문 생성 메서드 시나리오
//    주문을 한 멤버를 찾고, 주문한 아이템을 찾고, 배송을 생성하고, ㅇ


//    주문 취소

    @Transactional
    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findOne(orderId);
        order.cancel();
//      엔티티만 이렇게 변경 하면 JPA가 더티체킹으로 변경된 내역들의 쿼리를 알아서 DB에 적용시켜주는 쿼리를 날려준다.
    }

//    public List<Order> findOrders(OrderSearch orderSearch) {
//        return orderRepository.findAll(orderSearch);
//    }

}


// 주문 서비스 요약 : 주문 생성, 주문 취소, 주문 검색