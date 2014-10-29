package pl.gda.pg.eti.kask.javaee.jsf.entities.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author pc222
 */
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MagNameValidator.class)
public @interface MagName {

  String message() default "{pl.gda.pg.eti.kask.javaee.jsf.entities.validators.MagName.BAD_NAME}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String regex() default "";
}
