package com.lifetea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.lifetea.controller"})
public class SwaggerConfig {

    private ApiInfo initApiInfo() {
        ApiInfo apiInfo = new ApiInfo("车险营销系统",//大标题
                initContextInfo(),//简单的描述
                "1.0.0",//版本
                "服务条款",
                "https://github.com/lifetea",
                "lifetea",//链接显示文字
                "http://www.anfast.com"//网站链接
        );

        return apiInfo;
    }

    private String initContextInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("车险营销系统API");
        return sb.toString();
    }


    @Bean
    public Docket restfulApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("RestfulApi")
//                .genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
//                .paths(doFilteringRules())
                .build()
                .apiInfo(initApiInfo());
    }

//    /**
//     * 设置过滤规则
//     * 这里的过滤规则支持正则匹配
//     *
//     * @return
//     */
//    private Predicate<String> doFilteringRules() {
//        return or(
//                regex("/hello.*"),
//                regex("/vehicles.*")
//        );
//    }
}