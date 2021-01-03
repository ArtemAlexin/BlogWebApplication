package ru.myproject.first_project.validators.DTOValidators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.myproject.first_project.domain.VerificationToken;
import ru.myproject.first_project.service.VerificationTokenService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
@Component
public class ValidatorVerificationToken implements ConstraintValidator<ValidVerificationToken, String> {
   private final VerificationTokenService verificationToken;
   @Autowired
   public ValidatorVerificationToken(VerificationTokenService verificationToken) {
      this.verificationToken = verificationToken;
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      VerificationToken token;
      return Objects.nonNull(obj) && Objects.nonNull(token =
              verificationToken.findByToken(obj)) && token.isValid();
   }
}
