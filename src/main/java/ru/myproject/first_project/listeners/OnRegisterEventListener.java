package ru.myproject.first_project.listeners;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.events.OnRegisterEvent;
import ru.myproject.first_project.service.UserServiceInterface;
import ru.myproject.first_project.service.VerificationTokenService;

import javax.validation.Valid;
import java.net.InetAddress;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

import static ru.myproject.first_project.controller.RegisterConfirmationController.*;

@Component
public class OnRegisterEventListener extends AbstractEmailSendListener implements ApplicationListener<OnRegisterEvent> {
    private final VerificationTokenService verificationTokenService;

    @Autowired
    public OnRegisterEventListener(UserServiceInterface userService, JavaMailSender mailSender,
                                   VerificationTokenService verificationTokenService) {
        super(mailSender, userService);
        this.verificationTokenService = verificationTokenService;
    }
    @Async
    @Override
    public void onApplicationEvent(OnRegisterEvent event) {
        User user = event.getUser();
        String token = generateUUID(s -> Objects.nonNull(verificationTokenService.findByToken(s)));
        verificationTokenService.registerVerificationToken(user, token);
        sendEmail(user, "Password Confirmation",
                "To confirm registration follow the link",
                event.getAppUrl() + "/registerConfirm?token=" + token);
    }
}
