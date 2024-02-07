package jpabook.japshop.domian.Item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("B")
public class Book extends Item{
    private String author;
    private String isbn;

}
