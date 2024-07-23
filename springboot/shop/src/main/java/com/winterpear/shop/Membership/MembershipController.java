package com.winterpear.shop.Membership;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.Authenticator;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MembershipController {
    
    private final MembershipService membershipService;
    private final MembershipRepository membershipRepository;

    @GetMapping("/membership")
    public String membership () {
        return "membership.html";
    }
    
    @PostMapping("/signup")
    public String signUp (@RequestParam Map<String, String> formData) {
        System.out.println(formData);
        String id = formData.get("id");
        String pw = formData.get("pw");
        String email = formData.get("email");
        String username = formData.get("username");
        membershipService.saveId(id, pw, email, username);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login () {
        var result = membershipRepository.findByUserid("test");
//        System.out.println(result.get().getUserid());
        return "login.html";
    }

    @GetMapping("/my-page")
    public String myPage (Authentication auth) {
//        System.out.println(auth);
//        System.out.println(auth.getName());
//        System.out.println(auth.isAuthenticated());

        CustomUser result = (CustomUser) auth.getPrincipal();
//        System.out.println(result.getDisplayName());
        return "mypage.html";
    }

    @GetMapping("/logout")
    public String logOut () {
        return "login.html";
    }
}
