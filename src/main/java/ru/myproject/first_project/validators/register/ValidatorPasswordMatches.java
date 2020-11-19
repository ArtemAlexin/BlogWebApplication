package ru.myproject.first_project.validators.register;

import ru.myproject.first_project.domain.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;


public class ValidatorPasswordMatches implements ConstraintValidator<PasswordsMatches, User> {
    @Override
    public boolean isValid(User value, ConstraintValidatorContext context) {
        return Objects.nonNull(value.getPassword()) && value.getPassword().equals(value.getPasswordConfirmation());
    }
}
