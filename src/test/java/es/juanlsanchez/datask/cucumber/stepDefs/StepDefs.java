package es.juanlsanchez.datask.cucumber.stepDefs;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

import es.juanlsanchez.datask.Application;
import es.juanlsanchez.datask.security.SecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = {Application.class, SecurityConfiguration.class})
public abstract class StepDefs {

  protected ContainerDefs containerDefs;
  protected TransactionStatus transaction;
  @Inject
  protected PageableHandlerMethodArgumentResolver pageableArgumentResolver;
  @Inject
  protected PlatformTransactionManager transactionManager;

}
