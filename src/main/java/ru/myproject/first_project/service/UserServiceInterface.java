package ru.myproject.first_project.service;

import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.domain.VerificationToken;
import ru.myproject.first_project.exceptions.UserAlreadyExistsException;

public interface UserServiceInterface {
    User save(User user);
    User findByLoginOrEmailAndPassword(String loginOrEmail, String password);
    User findByLogin(String login);
    User findByEmail(String email);
    User findById(long id);
    void setEnabled(long id);
    void updatePassword(long id, String password);
}

