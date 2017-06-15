package es.juanlsanchez.datask.web.dto;

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
public class UserDataDTO {

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
