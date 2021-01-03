package ru.myproject.first_project.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.myproject.first_project.service.UserService;
import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.utils.MySimpleMailSender;

import java.util.UUID;
import java.util.function.Predicate;

@Component
public abstract class AbstractEmailSendListener {
    protected final MySimpleMailSender mailSender;
    protected final UserService userService;
    @Autowired
    public AbstractEmailSendListener(MySimpleMailSender mailSender, UserService userService) {
        this.mailSender = mailSender;
        this.userService = userService;
    }
    protected void sendEmail(User user, String subject, String message) {
       sendEmail(user.getEmail(), subject, message);
    }
    protected void sendEmail(User user, String subject, String message, String appUrl) {
        sendEmail(user, subject, message + appUrl);
    }
    protected void sendEmail(String email, String subject, String message) {
        mailSender.send(email, subject, message + System.lineSeparator());
    }
    protected String generateUUID(Predicate<String> f) {
        String token = UUID.randomUUID().toString();
        while (f.test(token)) {
            token = UUID.randomUUID().toString();
        }
        return token;
    }
}
