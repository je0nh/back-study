package com.winterpear.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    
    private final ItemRepository itemRepository;

    @GetMapping("/list")
    String list(Model model) {
//        JPS로 데이터베이스에서 데이터 출력하기
        List<Item> result = itemRepository.findAll();
        System.out.println(result.get(0).price);
        var a = new Item();
        System.out.println(a.toString());

//         List 자료형
//        List<Object> a = new ArrayList<>();
        
        model.addAttribute("items", result);
        return "list.html";
    }
}
