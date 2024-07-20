package com.winterpear.shop.Membership;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipService {
    
    private final MembershipRepository membershipRepository;
    private final PasswordEncoder passwordEncoder;
    
    public void saveId (String id, String pw, String email, String username) {
        Membership member = new Membership();
        String password = passwordEncoder.encode(pw);
        member.setUserid(id);
        member.setPassword(password);
        member.setEmail(email);
        member.setUsername(username);
        membershipRepository.save(member);
    }
    
}
