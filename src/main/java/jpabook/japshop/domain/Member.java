package jpabook.japshop.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;


    @Embedded
    private Address address; //여담으로 Embedded, Embeddable 둘 중 한가지만 적어도 작동 한다

    @OneToMany(mappedBy = "member")
//    @JoinColumn(name = "ORDER_ID")
    private List<Order> orders = new ArrayList<>();


}
