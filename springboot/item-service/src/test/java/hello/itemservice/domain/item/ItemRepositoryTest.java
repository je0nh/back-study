package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ItemRepositoryTest {
    
    ItemRepository itemRepository = new ItemRepository();
    
    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }
    
    @Test
    void save() {
        Item item = new Item("itmeA", 10000, 3);
        
        Item savedItem = itemRepository.save(item);

        Item findItem = itemRepository.findById(item.getId());

        assertThat(findItem).isEqualTo(savedItem);
    }
    
    @Test
    void findAll() {
        Item itme1 = new Item("itmeA", 10000, 3);
        Item itme2 = new Item("itmeb", 40000, 5);
        
        itemRepository.save(itme1);
        itemRepository.save(itme2);
        
        List<Item> result = itemRepository.findAll();
        
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itme1, itme2);
    }
    
    @Test
    void updateItem() {
        Item item = new Item("itmeA", 10000, 3);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        Item updateParam = new Item("item2", 40000, 329);
        itemRepository.update(itemId, updateParam);

        Item findItem = itemRepository.findById(itemId);
        
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}
