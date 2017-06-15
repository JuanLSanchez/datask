package es.juanlsanchez.datask.config;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Configuration
@ConfigurationProperties(prefix = "app.properties", ignoreUnknownFields = false)
public class AppConfig {

  private final Security security = new Security();
  private final CorsConfiguration cors = new CorsConfiguration();
  private final SwaggerProperties swagger = new SwaggerProperties();
  @Value("${spring.application.version}")
  private final String version = new String();
  @Value("${spring.application.name}")
  private final String name = new String();
  @Value("${spring.application.prefix}")
  private final String prefix = new String();

  @Getter
  @Setter
  public static class SwaggerProperties {
    private String name;
    private String termsOfServiceUrl;
    private String description;
    private String licenseUrl;
    private String license;
  }


  // Internal class
  public static class Security {

    @Getter
    private final Authentication authentication = new Authentication();

    @Getter
    @Setter
    public static class Authentication {

      @NotEmpty
      private final Jwt jwt = new Jwt();

      @Getter
      @Setter
      public static class Jwt {

        private String secret;

        private long tokenValidityInSeconds = 1800;
        private long tokenValidityInSecondsForRememberMe = 2592000;

      }
    }
  }

}
