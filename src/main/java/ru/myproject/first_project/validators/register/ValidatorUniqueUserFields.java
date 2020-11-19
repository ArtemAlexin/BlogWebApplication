package ru.myproject.first_project.validators.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.service.UserServiceInterface;

@Component
public class ValidatorUniqueUserFields implements Validator {
    private final UserServiceInterface userService;
    @Autowired
    public ValidatorUniqueUserFields(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            User user = (User)target;
            if(userService.findByLogin(user.getLogin()) != null) {
                errors.rejectValue("login", "login.used", "Login is already used");
            }
            if(userService.findByEmail(user.getEmail()) != null) {
                errors.rejectValue("email", "email.used", "Email is already used");
            }
        }
    }
}
