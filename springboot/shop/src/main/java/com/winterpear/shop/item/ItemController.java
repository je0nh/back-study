package com.winterpear.shop.item;

import com.winterpear.shop.comment.Comment;
import com.winterpear.shop.comment.CommentDTO;
import com.winterpear.shop.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class ItemController {
    
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final CommentService commentService;

    @GetMapping("/list")
    String list(Model model) {
//        JPS로 데이터베이스에서 데이터 출력하기
        List<Item> result = itemService.product();
//        System.out.println(result.get(0).price);
        var a = new Item();
//        System.out.println(a.toString());

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
        
//        test.put("name", "kim");
//        test.put("age", 20);
//        System.out.println(test.get("name"));
        
//        DB 저장
//        Item item = new Item();
//        item.setTitle(formData.get("title"));
//        item.setPrice(Integer.parseInt(formData.get("price")));
//        itemRepository.save(item);
        
        String title = formData.get("title");
        Integer price = Integer.parseInt(formData.get("price"));
        
        itemService.saveItem(title, price);
        
        return "redirect:/list";
    }

    // ModelAttribute를 사용하면 setter을 이용하지 않고 바로 받은 요소를 DB에 넣을 수 있음
//    @PostMapping("/product")
//    String productPost(@ModelAttribute Item item) {
//        System.out.println(item); // Item(id=id, title=input_title, price=input_price)
//        itemRepository.save(item);
//        return "redirect:/list";
//    }

    @GetMapping("/detail/{id}")
    String detail(@PathVariable Integer id, Model model) {
//        throw new Exception();
        Optional<Item> result = itemService.productId(id);
        List<CommentDTO> comments = commentService.getCommentUsers(id);
//        System.out.println(comments);
        
        if (result.isPresent()) {
//            System.out.println(result.get());
            model.addAttribute("item", result.get());
            model.addAttribute("comments", comments);
            return "detail.html";
        } else {
            return "redirect:/list";
        }
                 
    }

//        DB 데이터 변경
    @GetMapping("/detail/{id}/management")
    public String productManagement(@PathVariable Integer id, Model model) {
        Optional<Item> pid = itemService.productId(id);
        Item item = pid.get();
        model.addAttribute("item", item);
        return "management.html";
    }
    
    @PostMapping("/detail/{id}/edit")
    public String productEdit (@PathVariable Integer id, @RequestParam Map<String, String> formData, Model model) {
        Optional<Item> pid = itemService.productId(id);
        Item item = pid.get();
        model.addAttribute("item", item);
        
        String newTitle = formData.get("title");
        Integer newPrice = Integer.parseInt(formData.get("price"));
        itemService.modifyItem(id, newTitle, newPrice);
        
        return "redirect:/list";
    }

    @PostMapping("/test1")
    public String test1(@RequestBody Map<String, Object> body) {
        System.out.println(body);
        return "redirect:/list";
    }

    @GetMapping("/test1")
    public String test1(@RequestParam String name) {
        System.out.println(name);
        return "redirect:/list";
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

//    hashing
    @GetMapping("/test2")
    public String test2() {
        var result = new BCryptPasswordEncoder().encode("asdf");
//        System.out.println(result);
        return "redirect:/list";
    }

    @GetMapping("/list/page/{page}")
    String getListPage(Model model, @PathVariable Integer page) {
        Page<Item> items =  itemRepository.findPageBy(PageRequest.of(page - 1, 5));
        model.addAttribute("items", items);
        return "list.html";
    }
    
    @PostMapping("/search")
    String postSearch(@RequestParam String searchText, Model model) {
        
        var result = itemRepository.rawQuery1(searchText);
        System.out.println(result);
        model.addAttribute("items", result);
        
        return "list.html";
    }
        
}
