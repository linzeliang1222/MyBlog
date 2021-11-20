package top.linzeliang.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Description: Swagger 配置类
 * @Author: LinZeLiang
 * @Date: 2021-08-01
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .groupName("blog")
                .enable(true)
                .select()
                // RequestHandlerSelectors，配置要扫描接口的方式
                .apis(RequestHandlerSelectors.basePackage("top.linzeliang.blog.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("林泽良",
                "https://linzeliang.top",
                "linzeliang1222@gmail.com");
        return new ApiInfo(
                "Blog接口文档",
                "提供Blog的接口及测试",
                "v0.21",
                "https://linzeliang.top",
                contact,
                "Apache 3.0",
                "https://www.apache.org/",
                new ArrayList<>()
        );
    }
}