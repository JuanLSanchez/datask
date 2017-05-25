package es.juanlsanchez.datask.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import es.juanlsanchez.datask.annotation.PastLocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(indexes = {@Index(columnList = "incomeDate")})
public class Income extends UserObject {
  // Attributes -------------------------------------------------------------
  @NotNull
  @PastLocalDate
  private LocalDate incomeDate;
  @NotBlank
  private String name;
  private String nif;
  private double base;
  @Range(min = 0, max = 100)
  private int iva;

  // Relationships------------------------------------------------------------
}
