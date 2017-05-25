package es.juanlsanchez.datask.security;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import es.juanlsanchez.datask.security.jwt.JWTConfigurer;
import es.juanlsanchez.datask.security.jwt.TokenProvider;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Inject
  private UserDetailsService userDetailsService;

  @Inject
  private TokenProvider tokenProvider;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().headers().frameOptions().disable();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeRequests().antMatchers("/api/authenticate").permitAll();
    http.authorizeRequests().antMatchers("/manage/health").permitAll();
    http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
    http.authorizeRequests().antMatchers("/**").hasAuthority(AuthoritiesConstants.USER);
    http.authorizeRequests().antMatchers("/**").hasAnyAuthority(AuthoritiesConstants.MANAGE);
    http.authorizeRequests().antMatchers("/**").hasAuthority(AuthoritiesConstants.ADMIN);
    http.authorizeRequests().anyRequest().hasAuthority(AuthoritiesConstants.ADMIN);
    http.apply(securityConfigurerAdapter());
    http.exceptionHandling();

  }

  /**
   * Bean to use ?#{princiap} annotation y JPA
   */
  @Bean
  public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
    return new SecurityEvaluationContextExtension();
  }

  private JWTConfigurer securityConfigurerAdapter() {
    return new JWTConfigurer(tokenProvider);
  }
}
