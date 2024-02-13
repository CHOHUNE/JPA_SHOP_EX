package jpabook.japshop.domain.Item;

import jakarta.persistence.*;
import jpabook.japshop.domain.Category;
import jpabook.japshop.domain.exception.NotEnoughStockException;
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


    @ManyToMany(mappedBy = "items")
//    @JoinColumn(name="CATEGORY_ID")
    private List<Category> categories = new ArrayList<>();


//    비즈니스 로직 - 엔티티 안에서 비즈니스 로직을 작성함 - 객체지향적..
//    getter로 가지고 오고 더하고 빼고 다시 set 하고 하는게 아니라
//    데이터를 가지고 있는 쪽에 비즈니스 메서드를 작성 하는게 객체의 응집력이 있음.. MSA 구조와 비슷?...
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) throws NotEnoughStockException {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("nedd more stock");
        }
        this.stockQuantity = restStock;
    }
}
