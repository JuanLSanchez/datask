package es.juanlsanchez.datask.web.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.juanlsanchez.datask.domain.enumeration.EnumPanelStatus;
import es.juanlsanchez.datask.domain.enumeration.EnumTaskStatus;
import lombok.Data;

@Data
public class PanelDetailsDTO {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("status")
  private EnumPanelStatus status;

  @JsonProperty("orderPanel")
  private Integer orderPanel;

  @JsonProperty("creationDate")
  private LocalDate creationDate;

  @JsonProperty("defaultTaskStatus")
  private EnumTaskStatus defaultTaskStatus;

  @JsonProperty("projectId")
  private Long projectId;

}
