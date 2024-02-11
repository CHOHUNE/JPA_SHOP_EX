package jpabook.japshop.Controller;


import jpabook.japshop.Service.ItemService;
import jpabook.japshop.domian.Item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());

        return "items/createItemFrom";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStcokQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

//        Book book = new Book();
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStcokQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
//        이런 setter 가 여러개 있는 설계 보다 create 으로 묶는 게 더 좋은 설계이다.


        itemService.saveItem(book);
        return "redirect:/";

    }



    }

