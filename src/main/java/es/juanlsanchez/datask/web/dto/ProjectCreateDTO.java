package es.juanlsanchez.datask.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.juanlsanchez.datask.domain.enumeration.EnumProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreateDTO {

  @JsonProperty("id")
  private Long id;

  @NotEmpty
  @JsonProperty("name")
  private String name;

  @JsonProperty("status")
  private EnumProjectStatus status;

  @JsonProperty("completedEstimated")
  private Integer completedEstimated;

  @JsonProperty("budgetId")
  private Long budgetId;

  @JsonProperty("companyId")
  private Long companyId;

}
