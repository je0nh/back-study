package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        
        log.info("username:{}, age:{}", username, age);
        
        response.getWriter().write("ok");
    }
    
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("memberName:{}, memberAge:{}", memberName, memberAge);
        return "ok"; // @RestController와 같은 효과
    }
    
    // 스프링 부트 3.2 부터 파라미터 이름을 동일하게 적는 방법은 문제가 있음
    // v2 방법을 권장
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam("username") String username,
                                 @RequestParam("age") int age) {
        log.info("username:{}, age:{}", username, age);
        return "ok"; // @RestController와 같은 효과
    }
    
    // int는 null을 받을수 없기 때문에 Inteager로 타입을 변경해주어야 함
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam("username") String username, 
                                       @RequestParam(value = "age", required = false) Integer age) {
        log.info("username:{}, age:{}", username, age);
        return "ok"; // @RestController와 같은 효과
    }
    
    // username=  이라고 해도 기본값으로 설정한 것이 나옴
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(value = "username", defaultValue = "guest") String username, 
                                      @RequestParam(value = "age", defaultValue = "-1") Integer age) {
        log.info("username:{}, age:{}", username, age);
        return "ok"; // @RestController와 같은 효과
    }
    
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
    
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
        
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData:{}", helloData.toString());

        return "ok";
    }
    
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData:{}", helloData.toString());

        return "ok";
    }
}
