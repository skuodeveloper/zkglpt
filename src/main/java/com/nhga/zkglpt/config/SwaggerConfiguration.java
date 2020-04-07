package com.nhga.zkglpt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created on 2017年12月22日11:03:43
 *
 * @author Appleyk
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nhga.zkglpt.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 访问地址：localhost:8015/swagger-ui.html#/
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTFUL API DOC")
                .description("Spring-Boot--RESTFUL风格的接口文档在线自动生成")
                .termsOfServiceUrl("http://blog.csdn.net/appleyk")
                .version("1.0")
                .licenseUrl("http://localhost:8015/")
                .build();
    }
}