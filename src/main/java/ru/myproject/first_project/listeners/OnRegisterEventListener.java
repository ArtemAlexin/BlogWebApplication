package ru.myproject.first_project.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.events.OnRegisterEvent;
import ru.myproject.first_project.service.UserService;
import ru.myproject.first_project.service.VerificationTokenServiceImpl;
import ru.myproject.first_project.utils.MySimpleMailSender;

import java.util.Objects;

@Component
public class OnRegisterEventListener extends AbstractEmailSendListener implements ApplicationListener<OnRegisterEvent> {
    private final VerificationTokenServiceImpl verificationTokenService;

    @Autowired
    public OnRegisterEventListener(UserService userService, MySimpleMailSender mailSender,
                                   VerificationTokenServiceImpl verificationTokenService) {
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
