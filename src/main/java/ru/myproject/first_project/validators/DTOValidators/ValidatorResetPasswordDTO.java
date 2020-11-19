package ru.myproject.first_project.validators.DTOValidators;

import ru.myproject.first_project.domain.DTO.ResetPasswordDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatorResetPasswordDTO implements ConstraintValidator<ResetPasswordDTOValid, ResetPasswordDTO> {

   @Override
   public boolean isValid(ResetPasswordDTO value, ConstraintValidatorContext context) {
      return value.getPassword().equals(value.getPasswordConfirmation());
   }
}
