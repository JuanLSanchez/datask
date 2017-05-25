package es.juanlsanchez.datask.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.juanlsanchez.datask.annotation.PastLocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDTO {

  private Long id;
  @NotNull
  @PastLocalDate
  @JsonProperty("income_date")
  private LocalDate incomeDate;
  @NotBlank
  @JsonProperty("name")
  private String name;
  @JsonProperty("nif")
  private String nif;
  @JsonProperty("base")
  private double base;
  @Range(min = 0, max = 100)
  @JsonProperty("iva")
  private int iva;

}
