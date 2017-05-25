package es.juanlsanchez.datask.cucumber.stepDefs;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.inject.Inject;

import org.mockito.MockitoAnnotations;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import es.juanlsanchez.datask.web.error.ExceptionTranslator;
import es.juanlsanchez.datask.web.rest.AccountResource;

public class AccountDefs extends StepDefs {

  @Inject
  private AccountResource accountResource;
  @Inject
  private FilterChainProxy springSecurityFilterChain;

  @Before
  public void setup() {
    this.containerDefs = ContainerDefs.getInstance();
    transaction = transactionManager.getTransaction(new DefaultTransactionDefinition());
  }

  @After
  public void afterScenario() {
    transactionManager.rollback(transaction);
  }


  // Given --------------------------------------
  @Given("^the account resource$")
  public void the_account_resource() {

    final StaticApplicationContext applicationContext = new StaticApplicationContext();
    applicationContext.registerSingleton("exceptionHandler", ExceptionTranslator.class);

    final WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
    webMvcConfigurationSupport.setApplicationContext(applicationContext);

    this.containerDefs.setRestUserMockMvc(MockMvcBuilders.standaloneSetup(this.accountResource)
        .setCustomArgumentResolvers(this.pageableArgumentResolver)
        .setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver())
        .addFilters(springSecurityFilterChain).build());
    MockitoAnnotations.initMocks(this);
  }

  // Then ----------------------------------

  @Then("^the account is from the user$")
  public void the_account_is_from_the_user() throws Exception {
    this.containerDefs.getAction()
        .andExpect(jsonPath("$.login").value(this.containerDefs.getLoginDTO().getUsername()));

  }

}
