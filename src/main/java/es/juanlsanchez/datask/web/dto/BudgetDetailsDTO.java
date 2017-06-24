package es.juanlsanchez.datask.web.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDetailsDTO {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("ownAmount")
  private Double ownAmount;

  @JsonProperty("hours")
  private Double hours;

  @JsonProperty("billingDate")
  private LocalDate billingDate;

  @JsonProperty("startDate")
  private LocalDate startDate;

  @JsonProperty("endDate")
  private LocalDate endDate;

  @JsonProperty("projectId")
  private Long projectId;

}
