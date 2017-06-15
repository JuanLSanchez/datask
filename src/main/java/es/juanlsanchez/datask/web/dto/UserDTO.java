package es.juanlsanchez.datask.web.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("login")
  private String login;

  @JsonProperty("activated")
  private boolean activated;

  @JsonProperty("creationMoment")
  private Instant creationMoment;

  @JsonProperty("authorities")
  private List<String> authorities;


}
