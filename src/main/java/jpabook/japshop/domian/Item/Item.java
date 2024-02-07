package jpabook.japshop.domian.Item;

import jakarta.persistence.*;
import jpabook.japshop.domian.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
public abstract class Item {

    @GeneratedValue
    @Id
    @Column(name="ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany
    @JoinColumn(name="CATEGORY_ID")
    List<Category> categories = new ArrayList<>();



}
