package ru.myproject.first_project.events;

import org.springframework.context.ApplicationEvent;
import ru.myproject.first_project.domain.User;

public class OtpEvent extends ApplicationEvent {
    private String code;
    private User user;
    public OtpEvent(User user, String code) {
        super(user);
        this.user = user;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public User getUser() {
        return user;
    }
}
