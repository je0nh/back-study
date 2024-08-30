package hello.itemservice.domain.item;

import lombok.Data;

@Data // 위험하니까 @Getter @Setter을 사용하는것을 추천
public class Item {
    
    private long id;
    private String itemName;
    private Integer price;
    private Integer quantity;
    
    public Item() {}

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
