package jpabook.japshop.Controller;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberForm {


    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;

//    private Address address;
//
    private String city;
    private String street;
    private String zipcode;

}
