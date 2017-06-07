package es.juanlsanchez.datask.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A DTO representing a user's credentials
 */
@Data
@AllArgsConstructor
public class LoginDTO {

  @NotNull
  @Size(min = 1, max = 50)
  @JsonProperty("username")
  private String username;

  @NotNull
  @Size(min = 4, max = 50)
  @JsonProperty("password")
  private String password;

  @JsonProperty("rememberme")
  private Boolean rememberMe;

}
