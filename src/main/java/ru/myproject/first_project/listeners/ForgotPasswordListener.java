package ru.myproject.first_project.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.myproject.first_project.domain.ResetPasswordToken;
import ru.myproject.first_project.events.ForgotPasswordEvent;
import ru.myproject.first_project.service.ResetUserTokenServiceInterface;
import ru.myproject.first_project.service.UserServiceInterface;

import java.io.InvalidClassException;
import java.util.Objects;

@Component
public class ForgotPasswordListener extends AbstractEmailSendListener implements ApplicationListener<ForgotPasswordEvent> {
    private final ResetUserTokenServiceInterface resetUserTokenService;

    @Autowired
    public ForgotPasswordListener(JavaMailSender mailSender, UserServiceInterface userService, ResetUserTokenServiceInterface resetUserTokenService) {
        super(mailSender, userService);
        this.resetUserTokenService = resetUserTokenService;
    }
    @Async
    @Override
    public void onApplicationEvent(ForgotPasswordEvent event) {
        String generatedToken = generateUUID(s -> Objects.nonNull(resetUserTokenService.findByToken(s)));
        ResetPasswordToken token = new ResetPasswordToken();
        token.setUser(event.getUser());
        token.setToken(generatedToken);
        if (Objects.isNull(resetUserTokenService.findByUser(token.getUser()))) {
            resetUserTokenService.save(token);
        } else {
            resetUserTokenService.updateToken(token.getUser(), generatedToken);
        }
        sendEmail(event.getUser(), "Reset Password",
                "To reset your password follow the link",
                event.getAppUrl() + "/resetPassword?token=" + generatedToken);
        //resetUserTokenService.setInvalid(generatedToken);
    }
}
