package jpabook.japshop.repository;


import jpabook.japshop.domain.OrderStatus;
import lombok.Data;

@Data
public class OrderSearch {

//    input 창에 주문번호, 회원명에 따라 동적 JPQL이 들어가야 한다.

    private String memberName; // 회언 이름
    private OrderStatus orderStatus; // ORDER , CANCEl





}
