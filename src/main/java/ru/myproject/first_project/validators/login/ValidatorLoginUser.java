package ru.myproject.first_project.validators.login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.myproject.first_project.domain.UserCredentials;
import ru.myproject.first_project.service.UserServiceInterface;

import java.util.Objects;

@Component
public class ValidatorLoginUser implements Validator {

    private final UserServiceInterface userService;
    @Autowired
    public ValidatorLoginUser(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCredentials.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(!errors.hasErrors()) {
            UserCredentials user = (UserCredentials) target;
            if (Objects.isNull(userService.findByLoginOrEmailAndPassword(user.getLoginOrEmail(), user.getPassword()))) {
                errors.rejectValue("password", "password.invalid", "Invalid login/email or password");
            }
        }
    }
}
