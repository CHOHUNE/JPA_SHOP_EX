package jpabook.japshop.domian;


import jakarta.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue
    @Column(name="CATEGORY_ID")
    private Long id;
    private String name;



}
