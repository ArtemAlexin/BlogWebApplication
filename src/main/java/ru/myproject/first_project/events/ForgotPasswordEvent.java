package ru.myproject.first_project.events;
import org.springframework.context.ApplicationEvent;
import ru.myproject.first_project.domain.User;

public class ForgotPasswordEvent extends ApplicationEvent {
    private String appUrl;
    private User user;

    public ForgotPasswordEvent(User user, String appUrl) {
        super(user);
        this.appUrl = appUrl;
        this.user = user;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
