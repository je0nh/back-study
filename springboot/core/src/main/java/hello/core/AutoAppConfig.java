package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // ComponentScan 한정하기
//        basePackages = "hello.core.member",
//        basePackageClasses = AutoAppConfig.class,
        // 다른 @Configuration을 bean에 등록하지 않기 위해서
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
