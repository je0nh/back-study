package com.winterpear.shop.item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    
    private final ItemRepository itemRepository;
    private final EntityManagerFactory emf;
    
    public void saveItem (String title, Integer price) {
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }
    
    public List<Item> product () {
        return itemRepository.findAll();
    }
    
    public Optional<Item> productId (Integer id) {
        return itemRepository.findById(id);
    }
    
//    DB 데이터 변경
    public void modifyItem (Integer id, String newTitle, Integer newPrice) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        Item item = em.find(Item.class, id);
        
        item.setTitle(newTitle);
        item.setPrice(newPrice);
        em.getTransaction().commit();
        
        em.close();
    }
    
    public void deleteItem (Integer id) {
        itemRepository.deleteById(id);
    }
}
