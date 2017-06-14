package es.juanlsanchez.datask.web.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.juanlsanchez.datask.domain.enumeration.EnumUserType;
import es.juanlsanchez.datask.security.RolEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

  @NotNull
  @JsonProperty("login")
  private String login;

  @JsonProperty("password")
  private String password;

  @NotNull
  @JsonProperty("activated")
  private boolean activated;

  @JsonProperty("authorities")
  private List<RolEnum> authorities;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("surname")
  private String surname;

  @NotNull
  @JsonProperty("email")
  private String email;

  @JsonProperty("officePhone")
  private String officePhone;

  @JsonProperty("typeUser")
  private EnumUserType typeUser;

  @NotNull
  @JsonProperty("companyId")
  private Long companyId;

}
