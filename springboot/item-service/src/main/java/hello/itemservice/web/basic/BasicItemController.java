package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    
    private final ItemRepository itemRepository;
    
    @GetMapping
    public String item(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }
    
    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }
    
    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }
    
//    @PostMapping("/add")
//    public String addItemV1(@RequestParam("itemName") String itemName, 
//                            @RequestParam("price") int price, 
//                            @RequestParam("quantity") Integer quantity, 
//                            Model model) {
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);
//        
//        itemRepository.save(item);
//        
//        model.addAttribute("item", item);
//        return "basic/item";
//    }
    
    // @ModelAttribute("xxx") -> ()안의 이름으로 model에 담아주는 역할까지 수행
//    @PostMapping("/add")
//    public String addItemV2(@ModelAttribute("item") Item item) {
//        itemRepository.save(item);
//        
////        model.addAttribute("item", item);
//        return "basic/item";
//    }
    
    // @ModelAttribute -> ()가 없으면 type의 이름의 첫글자가 소문자로 model에 담아주는 역할까지 수행
//    @PostMapping("/add")
//    public String addItemV3(@ModelAttribute Item item) {
//        itemRepository.save(item);
//        return "basic/item";
//    }
    
    // @ModelAttribute는 생략 가능
//    @PostMapping("/add")
//    public String addItemV4(Item item) {
//        itemRepository.save(item);
//        return "basic/item";
//    }
    
    // PRG 패턴 적용
//    @PostMapping("/add")
//    public String addItemV5(Item item) {
//        itemRepository.save(item);
//        return "redirect:/basic/items/" + item.getId();
//    }
    
    // RedirectAttribute 적용
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }
    
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }
    
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable("itemId") Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }
    
    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemb", 20000, 40));
    }
}
