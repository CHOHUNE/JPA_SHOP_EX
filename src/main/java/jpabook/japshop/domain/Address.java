package jpabook.japshop.domain;


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
