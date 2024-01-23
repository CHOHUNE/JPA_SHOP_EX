package jpabook.japshop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jpabook.japshop.domain.Item;
import lombok.Data;

@Entity
@DiscriminatorValue("B")
@Data
public class Book extends Item {
    private String author;
    private String isbn;


}
