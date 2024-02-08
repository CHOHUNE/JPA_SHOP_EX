package jpabook.japshop.repository;


import jakarta.persistence.EntityManager;
import jpabook.japshop.domian.Item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager entityManager;

    public void save(Item item) {

        if (item.getId() == null) {
            entityManager.persist(item);

        }else{
            entityManager.merge(item); //merge는 update와 비슷
        }
    }

    public Item findOne(Long id) {
        return entityManager.find(Item.class, id);
    }

    public List<Item> findAll() {
        return entityManager.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
