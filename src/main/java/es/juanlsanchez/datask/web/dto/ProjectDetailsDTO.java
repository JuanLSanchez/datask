package es.juanlsanchez.datask.web.dto;

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
public class ProjectDetailsDTO {

  @JsonProperty("id")
  private Long id;

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
