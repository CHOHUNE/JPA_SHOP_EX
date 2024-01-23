package jpabook.japshop.service;


import jpabook.japshop.domain.*;
import jpabook.japshop.repository.ItemRepository;
import jpabook.japshop.repository.MemberRepository;
import jpabook.japshop.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    //    주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

//        엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

//        배송정보 생성

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

//        주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

//        주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

//        주문 저장
        orderRepository.save(order);
//        딜리버리, 오더아이템은 캐스케이드 타입 all 이기 때문에 order 안에 있을 경우 한 번에 save 된다
//        이런 경우 완전히 사이클이 같은 경우에만 쓴다. 여러 작업이 겹칠 경우 예상치 못한 결과 초래

        return order.getId();
    }

//   주문 취소

    @Transactional
    public void cancelOrder(Long orderId){
        Order order= orderRepository.findOne(orderId);
        order.cancel();
    }


//    검색
}
