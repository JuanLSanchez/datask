package es.juanlsanchez.datask.web.dto;

import java.util.TimeZone;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuarterDTO {

  @NotNull
  @JsonProperty("year")
  private Integer year;
  @NotNull
  @Range(min = 0, max = 3)
  @JsonProperty("quarter")
  private Integer quarter;
  private TimeZone timeZone;

}
