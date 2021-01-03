package ru.myproject.first_project.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.myproject.first_project.domain.ResetPasswordToken;
import ru.myproject.first_project.events.ForgotPasswordEvent;
import ru.myproject.first_project.service.ResetUserTokenService;
import ru.myproject.first_project.service.UserService;
import ru.myproject.first_project.utils.MySimpleMailSender;

import java.util.Objects;

@Component
public class ForgotPasswordListener extends AbstractEmailSendListener implements ApplicationListener<ForgotPasswordEvent> {
    private final ResetUserTokenService resetUserTokenService;

    @Autowired
    public ForgotPasswordListener(MySimpleMailSender mailSender, UserService userService, ResetUserTokenService resetUserTokenService) {
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
    }
}
