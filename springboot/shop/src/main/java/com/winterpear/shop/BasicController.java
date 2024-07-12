package com.winterpear.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {
    @GetMapping("/")
    @ResponseBody
    String hello(){
        return "안녕하세요 반갑습니다";
    }
    
    @GetMapping("/webtoon")
    @ResponseBody
    String webtoon(){
        return "웹툰 페이지 입니다";
    }
    
    @GetMapping("/index")
    String index(){
        return "index.html";
    }
}
