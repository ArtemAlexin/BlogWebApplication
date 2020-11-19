package ru.myproject.first_project.domain.DTO;

import ru.myproject.first_project.validators.DTOValidators.UserPresent;
import ru.myproject.first_project.validators.ValidEmail;

public class ForgotPasswordDTO {

    @ValidEmail
    @UserPresent
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
