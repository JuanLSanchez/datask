package es.juanlsanchez.datask.web.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.juanlsanchez.datask.domain.enumeration.EnumUserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("login")
  private String login;

  @JsonProperty("creationMoment")
  private Instant creationMoment;

  @JsonProperty("authorities")
  private List<String> authorities;

  @JsonProperty("name")
  private String name;

  @JsonProperty("surname")
  private String surname;

  @JsonProperty("email")
  private String email;

  @JsonProperty("officePhone")
  private String officePhone;

  @JsonProperty("typeUser")
  private EnumUserType typeUser;

}
