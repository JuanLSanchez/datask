package es.juanlsanchez.datask.cucumber.stepDefs;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDate;
import java.util.List;

import com.google.common.collect.Lists;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;

public class RangeDefs extends StepDefs {

  @Before
  public void setup() {
    this.containerDefs = ContainerDefs.getInstance();
  }

  @Then("^the minimum is '(\\d{4}-\\d{2}-\\d{2})'$")
  public void the_minimum_is(String localDateString) throws Exception {
    this.containerDefs.getAction()
        .andExpect(jsonPath("$.min").value(stringToCompare(localDateString)));
  }

  @Then("^the maximum is '(\\d{4}-\\d{2}-\\d{2})'$")
  public void the_maximum_is(String localDateString) throws Exception {
    this.containerDefs.getAction()
        .andExpect(jsonPath("$.max").value(stringToCompare(localDateString)));
  }

  private List<Integer> stringToCompare(String localDateString) {
    LocalDate localDate = LocalDate.parse(localDateString);
    return Lists.newArrayList(localDate.getYear(), localDate.getMonthValue(),
        localDate.getDayOfMonth());
  }

}
