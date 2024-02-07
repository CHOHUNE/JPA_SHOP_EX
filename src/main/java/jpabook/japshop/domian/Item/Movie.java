package jpabook.japshop.domian.Item;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("M")
public class Movie extends Item {
    private String director;
    private String actor;

}
