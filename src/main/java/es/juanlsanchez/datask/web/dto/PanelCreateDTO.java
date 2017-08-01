package es.juanlsanchez.datask.web.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.juanlsanchez.datask.domain.enumeration.EnumPanelStatus;
import es.juanlsanchez.datask.domain.enumeration.EnumTaskStatus;
import lombok.Data;

@Data
public class PanelCreateDTO {

  @JsonProperty("name")
  private String name;

  @JsonProperty("status")
  private EnumPanelStatus status;

  @JsonProperty("orderPanel")
  private Integer orderPanel;

  @JsonProperty("defaultTaskStatus")
  private EnumTaskStatus defaultTaskStatus;

  @JsonProperty("projectId")
  @NotNull
  private Long projectId;

}
