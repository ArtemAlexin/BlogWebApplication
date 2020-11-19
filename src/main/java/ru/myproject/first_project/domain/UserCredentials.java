package ru.myproject.first_project.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserCredentials {
    @Size(max = 30)
    @NotEmpty
    private String loginOrEmail;
    @Size(min = 8, max = 20)
    @NotEmpty
    private String password;

    public String getLoginOrEmail() {
        return loginOrEmail;
    }

    public void setLoginOrEmail(String loginOrEmail) {
        this.loginOrEmail = loginOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
