package ru.myproject.first_project.domain.DTO;

import ru.myproject.first_project.validators.DTOValidators.ValidResetPasswordToken;

public class ResetPasswordTokenDTO {

    @ValidResetPasswordToken
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
