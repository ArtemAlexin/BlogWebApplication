package ru.myproject.first_project.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import ru.myproject.first_project.service.UserServiceInterface;
import ru.myproject.first_project.domain.User;

import java.util.UUID;
import java.util.function.Predicate;

@Component
public abstract class AbstractEmailSendListener {
    protected final JavaMailSender mailSender;
    protected final UserServiceInterface userService;
    @Autowired
    public AbstractEmailSendListener(JavaMailSender mailSender, UserServiceInterface userService) {
        this.mailSender = mailSender;
        this.userService = userService;
    }
    protected void sendEmail(User user, String subject, String message, String appUrl) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message + System.lineSeparator()
                + appUrl);
        mailSender.send(simpleMailMessage);
    }
    protected String generateUUID(Predicate<String> f) {
        String token = UUID.randomUUID().toString();
        while (f.test(token)) {
            token = UUID.randomUUID().toString();
        }
        return token;
    }
}
