package com.winterpear.shop.comment;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CommentController {
    
    private final CommentService commentService;
    
    @PostMapping("/comment/{parentId}")
    public String comment (@RequestParam("comment") String content, @RequestParam(value = "timestamp") String date, @PathVariable Integer parentId) {
        String username = commentService.getCurrentUserId();
//        System.out.println(parentId);
        commentService.saveComment(username, date, content, parentId);
        return "redirect:/detail/" + parentId; 
    }
}
