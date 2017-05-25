package es.juanlsanchez.datask.web.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RangeDTO {

  private LocalDate min;
  private LocalDate max;

}
