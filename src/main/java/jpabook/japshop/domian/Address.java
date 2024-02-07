package jpabook.japshop.domian;


import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Embeddable
@Getter
@RequiredArgsConstructor
public class Address {

    private String city;
    private String zipcode;
    private String street;
}
