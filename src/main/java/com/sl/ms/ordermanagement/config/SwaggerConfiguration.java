package com.sl.ms.ordermanagement.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfiguration {

  public static final String AUTHORIZATION_HEADER = "Authorization";

  private ApiInfo apiInfo() {
    return new ApiInfo("Order Management REST API",
        "For Super MS Batch",
        "1.0",
        "Terms of service",
        new Contact("Veera", "", ""),
        "License",
        "www.google.com",
        Collections.emptyList());
  }

  @Bean
  public Docket newsApi() {
      return new Docket(DocumentationType.SWAGGER_2)
              .select()
              .apis(RequestHandlerSelectors.any())
              .paths(PathSelectors.any())
              .paths(Predicates.not(PathSelectors.regex("/error.*")))//<6>, regex must be in double 
//              .paths(Predicates.not(PathSelectors.regex("/test/*")))
              .paths(PathSelectors.regex("(?!/error).+")).paths(PathSelectors.regex("(?!/test).+"))
              .build()
              .securitySchemes(Lists.newArrayList(apiKey()))
              .securityContexts(Lists.newArrayList(securityContext()))
              .apiInfo(apiInfo())
              ;
  }

  @Bean
  SecurityContext securityContext() {
      return SecurityContext.builder()
              .securityReferences(defaultAuth())
              .forPaths(PathSelectors.any())
              .build();
  }

  List<SecurityReference> defaultAuth() {
      AuthorizationScope authorizationScope
              = new AuthorizationScope("global", "accessEverything");
      AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
      authorizationScopes[0] = authorizationScope;
      return Lists.newArrayList(
              new SecurityReference("JWT", authorizationScopes));
  }

  private ApiKey apiKey() {
      return new ApiKey("JWT", "Authorization", "header");
  }
}