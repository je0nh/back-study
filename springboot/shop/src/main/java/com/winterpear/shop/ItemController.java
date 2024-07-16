package com.winterpear.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ItemController {
    
    private final ItemRepository itemRepository;

    @GetMapping("/list")
    String list(Model model) {
//        JPS로 데이터베이스에서 데이터 출력하기
        List<Item> result = itemRepository.findAll();
//        System.out.println(result.get(0).price);
        var a = new Item();
        System.out.println(a.toString());

//         List 자료형
//        List<Object> a = new ArrayList<>();
        
        model.addAttribute("items", result);
        return "list.html";
    }
    
    @GetMapping("/write")
    String write(Model model) {
        return "write.html";
    }

    @PostMapping("/product")
    String productPost(@RequestParam Map<String, String> formData) {
        
//        map 자료형 이용하기
        Map<String, Object> test = new HashMap<>();
        
        test.put("name", "kim");
        test.put("age", 20);
        System.out.println(test.get("name"));
        
//        DB 저장
        Item item = new Item();
        item.setTitle(formData.get("title"));
        item.setPrice(Integer.parseInt(formData.get("price")));
        itemRepository.save(item);
        
        return "redirect:/list";
    }
}
