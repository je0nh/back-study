package com.winterpear.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnnouncementController {
    
    private final AnnouncementRepository announcementRepository;
    
    @GetMapping("/announce")
    String Announce (Model model) {
        List<Announcement> announceList = announcementRepository.findAll();
        System.out.println(announceList.get(0).date);
        model.addAttribute("announce", announceList);
        return "announce.html";
    }
}
