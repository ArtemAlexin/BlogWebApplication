package ru.myproject.first_project.service;

import ru.myproject.first_project.domain.Otp;
import ru.myproject.first_project.domain.User;

public interface OtpService {
    Otp findByUser(User user);
    void updateOtpById(long id, String newCode);
    void updateOrSave(User user, String code);
    void invalidate(User user);
}
