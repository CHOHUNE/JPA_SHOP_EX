package jpabook.japshop.Service;


import jpabook.japshop.domain.Item.Item;
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

    @Transactional
    public Item updateItem(Long itemId, int price, String name,int stockQuantity ){
        Item findItem = itemRepository.findOne(itemId);
        //id 를 기반으로 실제 영속 상태의 엔티티를 찾아옴
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

        return findItem;

        // itemRepository에 save를 호출한다거나 persist 할 필요 없다.
        // Transactional이 commit 을 하게 되고 JPA에서

    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);

    }

// 상품 서비스는 단순히 상품 레퍼지토리의 메서드를 위임만 하는 클래스이다.
//    경우에 따라서는 서비스단에서 제공하는 게 아니라 바로 레퍼지토리로 접근 해서 쓰는 것도 하나의 방법이다. ㄹㄹㄹㅅ저ㅕ9ㅐㅑㅣㅣㅣ 1
}
