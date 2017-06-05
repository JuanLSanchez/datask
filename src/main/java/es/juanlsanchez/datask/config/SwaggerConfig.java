package es.juanlsanchez.datask.config;

import java.text.MessageFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  private final AppConfig appConfig;

  public SwaggerConfig(final AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
        .apis(RequestHandlerSelectors.any()).paths(PathSelectors.ant(getPath())).build();
  }

  private String getPath() {
    return MessageFormat.format("/{0}/**", appConfig.getPrefix() + appConfig.getVersion());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title(appConfig.getSwagger().getName())
        .description(appConfig.getSwagger().getDescription())
        .termsOfServiceUrl(appConfig.getSwagger().getTermsOfServiceUrl())
        .license(appConfig.getSwagger().getLicense())
        .licenseUrl(appConfig.getSwagger().getLicenseUrl()).version(appConfig.getVersion()).build();
  }

}
