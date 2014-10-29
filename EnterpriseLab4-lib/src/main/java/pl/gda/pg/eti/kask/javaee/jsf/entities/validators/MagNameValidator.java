package pl.gda.pg.eti.kask.javaee.jsf.entities.validators;

import lombok.extern.java.Log;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.logging.Level;

/**
 * @author pc222
 */
@Log
public class MagNameValidator implements ConstraintValidator<MagName, String> {

  private String regex;
  

  @Override
  public void initialize(MagName constraintAnnotation) {
    regex = constraintAnnotation.regex();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if(!Character.isUpperCase(value.charAt(0))) {
        return false;
    }
    if (value.length() < 4) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(
        "{pl.gda.pg.eti.kask.javaee.jsf.entities.validators.MagName.TOO_SHORT}"
      ).addConstraintViolation();
      return false;
    }


    if (!regex.equals("") && !value.matches(regex)) {
      return false;
    }

    return true;
  }
}
