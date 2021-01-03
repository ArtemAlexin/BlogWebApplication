package ru.myproject.first_project.listeners;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.myproject.first_project.events.OtpEvent;
import ru.myproject.first_project.service.UserService;
import ru.myproject.first_project.utils.MySimpleMailSender;
@Component
public class OtpEventListener extends AbstractEmailSendListener implements ApplicationListener<OtpEvent> {
    public OtpEventListener(MySimpleMailSender mailSender, UserService userService) {
        super(mailSender, userService);
    }

    @Override
    @Async
    public void onApplicationEvent(OtpEvent event) {
        sendEmail(event.getUser(), "One time password code",
                "Here is your one time password code " + event.getCode());
    }
}
