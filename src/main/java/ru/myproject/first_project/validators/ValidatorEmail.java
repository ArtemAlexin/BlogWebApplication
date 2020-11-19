package ru.myproject.first_project.validators;

import ru.myproject.first_project.validators.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidatorEmail implements ConstraintValidator<ValidEmail, String> {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-+]" +
            "(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*" +
            "(.[A-Za-z]{2,})$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Matcher matcher = EMAIL_PATTERN.matcher(value);
        return matcher.matches();
    }
}
