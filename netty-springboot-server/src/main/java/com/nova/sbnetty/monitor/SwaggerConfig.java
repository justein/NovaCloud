package com.nova.sbnetty.monitor;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
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
 * @ClassName SwaggerConfig
 * @Description TODO
 * @Author Lyn
 * @Date 2019/3/1 0001 下午 5:24
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
@ConditionalOnExpression("'${swagger.enable}' == 'true'")

public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.crossoverjie.netty.action.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Netty Springboot Api Manager")
                .description("Netty Springboot integration API")
                .termsOfServiceUrl("http://127.0.0.1")
                .contact("Lyn")
                .version("1.0.0")
                .build();
    }
}
