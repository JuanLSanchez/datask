package es.juanlsanchez.datask.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import es.juanlsanchez.datask.validator.PastLocalDateValidator;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PastLocalDateValidator.class)
@Documented
public @interface PastLocalDate {

  boolean include() default true;

  String message() default "es.juanlsanchez.datask.annotation.PastLocalDate";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
