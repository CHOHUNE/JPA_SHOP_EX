package jpabook.japshop.domian.Item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("A")
public class Album extends Item{

    private String artist;
    private String etc;
}
