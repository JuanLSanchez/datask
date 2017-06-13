package es.juanlsanchez.datask.security;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
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
import es.juanlsanchez.datask.web.rest.AccountResource;
import es.juanlsanchez.datask.web.rest.ProjectResource;
import es.juanlsanchez.datask.web.rest.UserJWTResource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Inject
  private UserDetailsService userDetailsService;

  @Inject
  private Environment env;

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

    // Swagger
    http.authorizeRequests().antMatchers("/v2/api-docs", "/swagger-resources/**",
        "/configuration/ui", "/configuration/security", "/swagger-ui.html", "/webjars/**")
        .permitAll();

    // Health
    http.authorizeRequests().antMatchers("/manage/health").permitAll();

    // JS CORS and options
    http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll();

    // Authenticate
    String authenticateUrl =
        resolve(UserJWTResource.SECURITY_URL, UserJWTResource.SECURITY_AUTHENTICATE_URL);

    http.authorizeRequests().antMatchers(HttpMethod.POST, authenticateUrl).permitAll();

    // Account
    String meUrl = resolve(AccountResource.SECURITY_URL);
    String userDataUrl = resolve(AccountResource.SECURITY_URL, AccountResource.SECURITY_DATA);
    String companyUrl = resolve(AccountResource.SECURITY_URL, AccountResource.SECURITY_COMPANY);

    http.authorizeRequests().antMatchers(HttpMethod.GET, meUrl).authenticated();
    http.authorizeRequests().antMatchers(HttpMethod.GET, userDataUrl).authenticated();
    http.authorizeRequests().antMatchers(HttpMethod.GET, companyUrl).authenticated();

    // Project
    String projectUrl = resolve(ProjectResource.SECURITY_URL);

    http.authorizeRequests().antMatchers(HttpMethod.GET, projectUrl)
        .hasAnyAuthority(RolEnum.ADMIN.role(), RolEnum.MANAGER.role());

    // Others
    http.authorizeRequests().anyRequest().hasAuthority(RolEnum.Roles.ADMIN);

    http.authorizeRequests().antMatchers("/**").denyAll();

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

  private String resolve(String... text) {
    String result = "";
    for (String t : text) {
      result += this.env.resolvePlaceholders(t);
    }
    return result;
  }
}
