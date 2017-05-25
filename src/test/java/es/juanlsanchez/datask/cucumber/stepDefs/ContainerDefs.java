package es.juanlsanchez.datask.cucumber.stepDefs;

import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import es.juanlsanchez.datask.web.dto.LoginDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContainerDefs {

  // Attributes
  private ResultActions action;
  private MockMvc restUserMockMvc;
  private HttpHeaders httpHeaders;
  private Object responseObject;
  private LoginDTO loginDTO;
  private HashMap<String, String> params = new HashMap<String, String>();

  // Constructors
  private ContainerDefs() {}

  // Instance
  private static ContainerDefs instance = null;

  public static ContainerDefs getInstance() {
    if (instance == null) {
      instance = new ContainerDefs();
    }
    return instance;
  }

  // Get and Set

  public HttpHeaders getHttpHeaders() {
    if (this.httpHeaders == null) {
      this.httpHeaders = new HttpHeaders();
    }
    return this.httpHeaders;
  }

}
