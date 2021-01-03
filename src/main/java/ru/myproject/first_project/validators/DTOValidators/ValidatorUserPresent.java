package ru.myproject.first_project.validators.DTOValidators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.myproject.first_project.service.UserServiceImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class ValidatorUserPresent implements ConstraintValidator<UserPresent, String> {
   private final UserServiceImpl userService;
   @Autowired
   public ValidatorUserPresent(UserServiceImpl userService) {
      this.userService = userService;
   }

   public boolean isValid(String email, ConstraintValidatorContext context) {
      return userService.findByEmail(email) != null;
   }
}
