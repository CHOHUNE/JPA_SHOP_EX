package jpabook.japshop.domian;


import jakarta.persistence.*;
import jpabook.japshop.domian.Item.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue
    @Column(name="CATEGORY_ID")
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name="CATEGORY_ITEM_ID",
    joinColumns = @JoinColumn(name="CATEGORY_ID"),
    inverseJoinColumns = @JoinColumn(name="ITEM_ID"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy="parent")
//    @JoinColumn(name="child_id")
    private List<Category> child = new ArrayList<>();

    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }



}
