package ru.myproject.first_project.validators.DTOValidators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.myproject.first_project.domain.DTO.RegisterUserDTO;
import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.service.UserService;

@Component
public class ValidatorUniqueUserFields implements Validator {
    private final UserService userService;
    @Autowired
    public ValidatorUniqueUserFields(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterUserDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            RegisterUserDTO user = (RegisterUserDTO) target;
            if(userService.findByLogin(user.getLogin()) != null) {
                errors.rejectValue("login", "login.used", "Login is already used");
            }
            if(userService.findByEmail(user.getEmail()) != null) {
                errors.rejectValue("email", "email.used", "Email is already used");
            }
        }
    }
}
