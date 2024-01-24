package jpabook.japshop.repository;

import jpabook.japshop.domain.Order;
import jpabook.japshop.domain.OrderStatus;
import lombok.Data;

@Data
public class OrderSearch {

    private String memberName; // 회원 이름
    private OrderStatus orderStatus; //주문 상태 ORDER , CANCEL


}
