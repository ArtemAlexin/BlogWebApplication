package ru.myproject.first_project.validators.DTOValidators;

import ru.myproject.first_project.domain.DTO.RegisterUserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;


public class ValidatorPasswordMatches implements ConstraintValidator<PasswordsMatches, RegisterUserDTO> {
    @Override
    public boolean isValid(RegisterUserDTO value, ConstraintValidatorContext context) {
        return Objects.nonNull(value.getPassword()) && value.getPassword().equals(value.getPasswordConfirmation());
    }
}
