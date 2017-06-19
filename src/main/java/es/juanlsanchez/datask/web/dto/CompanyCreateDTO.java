package es.juanlsanchez.datask.web.dto;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.juanlsanchez.datask.domain.enumeration.EnumCompanyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateDTO {

  @JsonProperty("id")
  private Long id;

  @Size(min = 1, max = 100)
  @JsonProperty("name")
  private String name;

  @Size(max = 240)
  @JsonProperty("address")
  private String address;

  @JsonProperty("typeCompany")
  private EnumCompanyType typeCompany;

  @JsonProperty("subscriptionId")
  private Long subscriptionId;

}
