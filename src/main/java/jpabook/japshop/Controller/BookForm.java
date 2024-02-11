package jpabook.japshop.Controller;

import lombok.Data;

@Data
public class BookForm {
    private Long id;

    private String name;
    private int price;
    private int stcokQuantity;

    private String author;
    private String isbn;

}
