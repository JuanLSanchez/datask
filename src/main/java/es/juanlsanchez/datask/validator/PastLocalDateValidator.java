package es.juanlsanchez.datask.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import es.juanlsanchez.datask.annotation.PastLocalDate;

public class PastLocalDateValidator implements ConstraintValidator<PastLocalDate, LocalDate> {

  private boolean include;

  @Override
  public void initialize(PastLocalDate pastLocalDate) {
    this.include = pastLocalDate.include();
  }

  @Override
  public boolean isValid(LocalDate localDate, ConstraintValidatorContext context) {
    LocalDate now;
    if (this.include) {
      now = LocalDate.now().plusDays(1);
    } else {
      now = LocalDate.now();
    }
    return now.isAfter(localDate);
  }


}
