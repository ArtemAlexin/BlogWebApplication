package ru.myproject.first_project.service;

import ru.myproject.first_project.domain.ResetPasswordToken;
import ru.myproject.first_project.domain.User;

public interface ResetUserTokenServiceInterface {
    ResetPasswordToken save(ResetPasswordToken token);
    ResetPasswordToken findByUser(User user);
    ResetPasswordToken findByToken(String token);
    void updateToken(User user, String token);
    void setInvalid(long id);
    void setInvalid(String token);
}
