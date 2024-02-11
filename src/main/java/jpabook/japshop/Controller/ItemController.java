package jpabook.japshop.Controller;


import jpabook.japshop.Service.ItemService;
import jpabook.japshop.domian.Item.Book;
import jpabook.japshop.domian.Item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());

        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
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

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Book item = (Book) itemService.findOne(itemId);

        BookForm bookForm = new BookForm();
        bookForm.setId(item.getId());
        bookForm.setPrice(bookForm.getPrice());
        bookForm.setStockQuantity(bookForm.getStockQuantity());
        bookForm.setAuthor(item.getAuthor());
        bookForm.setIsbn(item.getIsbn());

        model.addAttribute("form", bookForm);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemdId}/edit")
    public String updateItem(@PathVariable String itemdId,@ModelAttribute("form") BookForm form , Model model) {

        Book book = new Book();
        book.setIsbn(form.getIsbn());
        book.setId(form.getId());
        book.setPrice(form.getPrice());
        book.setAuthor(form.getAuthor());
        book.setStockQuantity(form.getStockQuantity());
        book.setName(form.getName());

        itemService.saveItem(book);

        return "redirect:/items";

    }
}

