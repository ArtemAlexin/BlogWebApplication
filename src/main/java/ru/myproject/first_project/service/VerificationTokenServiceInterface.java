package ru.myproject.first_project.service;

import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.domain.VerificationToken;

public interface VerificationTokenServiceInterface {
    VerificationToken findByToken(String token);
    VerificationToken registerVerificationToken(User user, String token);
}
