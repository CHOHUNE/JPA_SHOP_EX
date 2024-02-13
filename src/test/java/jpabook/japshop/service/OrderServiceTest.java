package jpabook.japshop.service;


import jakarta.persistence.EntityManager;
import jpabook.japshop.Service.OrderService;
import jpabook.japshop.domain.Address;
import jpabook.japshop.domain.Item.Book;
import jpabook.japshop.domain.Member;
import jpabook.japshop.domain.Order;
import jpabook.japshop.domain.OrderStatus;
import jpabook.japshop.domain.exception.NotEnoughStockException;
import jpabook.japshop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {

        Member member = createMember("회원1"); //opt cmd + m 으로 메서드 분리

        Book book = createBook("시골 JPA", 1000, 10);


        int orderCount=2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());


    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량_초과() throws Exception {

        Book 시골_jpa = createBook("시골 JPA", 1000, 10);
        Member 회원1 = createMember("회원1");

        int ordercount=11;
        orderService.order(회원1.getId(), 시골_jpa.getId(), ordercount);

        fail("재고 수량 부족 예외가 발생해야 한다.!!!");

    }

    @Test
    public void 주문취소() throws Exception {

        Member member = createMember("회원1");
        Book book = createBook("냥냥", 1000, 10);
        int orderCount = 2;

        Long order = orderService.order(member.getId(), book.getId(), orderCount);
        orderService.cancelOrder(order);

//        취소 했을 때 재고가 멀쩡히 복구가 되었는지 ~

        Order one = orderRepository.findOne(order);

        assertEquals("주문취소시 상태는 캔슬이 되어야함",OrderStatus.CANCEL,one.getStatus());
        assertEquals("주문이 취소된 상품은 그만큼 재고가 증가 해야함",10,book.getStockQuantity());


    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name); //cmd opt p 로 파라메터 꺼내기 
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("서울","강가","123-123"));
        em.persist(member);
        return member;
    }
}
