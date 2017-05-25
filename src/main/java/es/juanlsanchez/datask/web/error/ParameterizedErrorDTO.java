package es.juanlsanchez.datask.web.error;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class ParameterizedErrorDTO implements Serializable {

  private static final long serialVersionUID = 1L;
  private final String message;
  private final String[] params;

  public ParameterizedErrorDTO(String message, String... params) {
    this.message = message;
    this.params = params;
  }

}
