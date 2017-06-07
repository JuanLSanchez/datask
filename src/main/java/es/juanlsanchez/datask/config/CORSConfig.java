package es.juanlsanchez.datask.config;

import java.text.MessageFormat;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CORSConfig {

  @Bean
  @ConditionalOnProperty(name = "app.properties.cors.allowed-origins")
  public CorsFilter corsFilter(AppConfig appConfig) {
    log.info("Registering CORS filter");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = appConfig.getCors();
    String path = MessageFormat.format("/{0}/**", appConfig.getPrefix() + appConfig.getVersion());
    source.registerCorsConfiguration(path, config);
    return new CorsFilter(source);
  }

}
