package jpabook.japshop.domian;


import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String city;
    private String zipcode;
    private String street;
}
