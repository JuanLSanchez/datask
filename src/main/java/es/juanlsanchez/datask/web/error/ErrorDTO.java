package es.juanlsanchez.datask.web.error;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String message;
  private final String description;
  private List<FieldErrorDTO> fieldErrors;

  public ErrorDTO(String message) {
    this(message, null);
  }

  public ErrorDTO(String message, String description) {
    this.message = message;
    this.description = description;
  }

  public void add(String objectName, String field, String message) {
    if (fieldErrors == null) {
      fieldErrors = new ArrayList<>();
    }
    fieldErrors.add(new FieldErrorDTO(objectName, field, message));
  }
}
