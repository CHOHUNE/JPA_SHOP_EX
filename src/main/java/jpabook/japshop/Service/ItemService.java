package jpabook.japshop.Service;


import jpabook.japshop.domian.Item.Item;
import jpabook.japshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItem() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);

    }

// 상품 서비스는 단순히 상품 레퍼지토리의 메서드를 위임만 하는 클래스이다.
//    경우에 따라서는 서비스단에서 제공하는 게 아니라 바로 레퍼지토리로 접근 해서 쓰는 것도 하나의 방법이다. ㄹㄹㄹㅅ저ㅕ9ㅐㅑㅣㅣㅣ 1
}
