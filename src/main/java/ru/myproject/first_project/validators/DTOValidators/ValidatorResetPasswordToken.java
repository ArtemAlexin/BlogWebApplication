package ru.myproject.first_project.validators.DTOValidators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.myproject.first_project.domain.ResetPasswordToken;
import ru.myproject.first_project.service.ResetUserTokenService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
@Component
public class ValidatorResetPasswordToken implements ConstraintValidator<ValidResetPasswordToken, String> {
   private final ResetUserTokenService resetUserTokenService;
   @Autowired
   public ValidatorResetPasswordToken(ResetUserTokenService resetUserTokenService) {
      this.resetUserTokenService = resetUserTokenService;
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      ResetPasswordToken token;
      return Objects.nonNull(obj) && Objects.nonNull(token = resetUserTokenService.findByToken(obj)) && token.isValid();
   }
}
