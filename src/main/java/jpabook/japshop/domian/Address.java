package jpabook.japshop.domian;


import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;

@Embeddable
@Getter
public class Address {

    private String city;
    private String zipcode;
    private String street;
}
