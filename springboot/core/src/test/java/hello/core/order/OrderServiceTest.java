package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    
//    MemberService memberService = new MemberServiceImpl();    
//    OrderService orderService = new OrderServiceImpl();
    
    MemberService memberService;
    OrderService orderService;
    
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    
    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        
        Order order = orderService.createOrder(memberId, "itemA", 5000);
        
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(500);
        Assertions.assertThat(order.getMemberId()).isEqualTo(memberId);
        Assertions.assertThat(order.getItemName()).isEqualTo("itemA");
    }
}
