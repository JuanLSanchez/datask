package es.juanlsanchez.datask.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class C3P0Config {
  /**
   * Constructor for ComboPooledDataSource bean.
   * 
   * @return Bean for DataSource definition with c3p0
   */
  @Bean(destroyMethod = "close")
  @ConfigurationProperties(prefix = "app.c3p0.datasource")
  public ComboPooledDataSource dataSource() {

    return new ComboPooledDataSource();
  }
}
