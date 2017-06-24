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
import es.juanlsanchez.datask.web.rest.BudgetResource;
import es.juanlsanchez.datask.web.rest.CompanyResource;
import es.juanlsanchez.datask.web.rest.ProjectResource;
import es.juanlsanchez.datask.web.rest.UserJWTResource;
import es.juanlsanchez.datask.web.rest.UserResource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final String MANAGER = RolEnum.MANAGER.role();
  private static final String ADMIN = RolEnum.ADMIN.role();

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
    String userCompanyUrl = resolve(AccountResource.SECURITY_URL, AccountResource.SECURITY_COMPANY);

    http.authorizeRequests().antMatchers(HttpMethod.GET, meUrl).authenticated();
    http.authorizeRequests().antMatchers(HttpMethod.GET, userDataUrl).authenticated();
    http.authorizeRequests().antMatchers(HttpMethod.GET, userCompanyUrl).authenticated();

    // User
    String userUrl = resolve(UserResource.SECURITY_URL);
    String userId = resolve(UserResource.SECURITY_URL, UserResource.SECURITY_ID);

    http.authorizeRequests().antMatchers(HttpMethod.GET, userUrl).hasAuthority(ADMIN);
    http.authorizeRequests().antMatchers(HttpMethod.POST, userUrl).hasAuthority(ADMIN);
    http.authorizeRequests().antMatchers(HttpMethod.GET, userId).authenticated();
    http.authorizeRequests().antMatchers(HttpMethod.PUT, userId).authenticated();
    http.authorizeRequests().antMatchers(HttpMethod.DELETE, userId).hasAuthority(ADMIN);

    // Company
    String companyUrl = resolve(CompanyResource.SECURITY_URL);
    String companyId = resolve(CompanyResource.SECURITY_URL, CompanyResource.SECURITY_ID);

    http.authorizeRequests().antMatchers(HttpMethod.GET, companyUrl).hasAnyAuthority(ADMIN,
        MANAGER);
    http.authorizeRequests().antMatchers(HttpMethod.POST, companyUrl).hasAnyAuthority(ADMIN,
        MANAGER);
    http.authorizeRequests().antMatchers(HttpMethod.GET, companyId).hasAnyAuthority(ADMIN, MANAGER);
    http.authorizeRequests().antMatchers(HttpMethod.PUT, companyId).hasAnyAuthority(ADMIN, MANAGER);
    http.authorizeRequests().antMatchers(HttpMethod.DELETE, companyId).hasAnyAuthority(ADMIN,
        MANAGER);

    // Project
    String projectUrl = resolve(ProjectResource.SECURITY_URL);
    String projectByPrincipal =
        resolve(ProjectResource.SECURITY_URL, ProjectResource.SECURITY_BY_PRINCIPAL);

    http.authorizeRequests().antMatchers(HttpMethod.GET, projectUrl).hasAnyAuthority(ADMIN,
        MANAGER);
    http.authorizeRequests().antMatchers(HttpMethod.GET, projectByPrincipal).authenticated();
    http.authorizeRequests().antMatchers(HttpMethod.POST, projectUrl).hasAnyAuthority(ADMIN,
        MANAGER);

    // Budget
    String budgetUrl = resolve(BudgetResource.SECURITY_URL);
    String budgetByPrincipal =
        resolve(BudgetResource.SECURITY_URL, BudgetResource.SECURITY_BY_PRINCIPAL);

    http.authorizeRequests().antMatchers(HttpMethod.GET, budgetUrl).hasAnyAuthority(ADMIN, MANAGER);
    http.authorizeRequests().antMatchers(HttpMethod.POST, budgetUrl).hasAnyAuthority(ADMIN,
        MANAGER);
    http.authorizeRequests().antMatchers(HttpMethod.GET, budgetByPrincipal).hasAnyAuthority(ADMIN,
        MANAGER);


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
