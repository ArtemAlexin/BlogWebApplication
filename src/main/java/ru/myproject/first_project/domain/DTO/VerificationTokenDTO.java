package ru.myproject.first_project.domain.DTO;

import ru.myproject.first_project.validators.DTOValidators.ValidVerificationToken;

public class VerificationTokenDTO {
    @ValidVerificationToken
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
