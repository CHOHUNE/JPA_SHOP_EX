package jpabook.japshop.repository;


import jpabook.japshop.domian.OrderStatus;
import lombok.Data;
import lombok.Getter;

@Data
public class OrderSearch {

//    input 창에 주문번호, 회원명에 따라 동적 JPQL이 들어가야 한다.

    private String memberName; // 회언 이름
    private OrderStatus orderStatus; // ORDER , CANCEl





}
