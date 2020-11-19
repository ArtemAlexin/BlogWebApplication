package ru.myproject.first_project.domain.DTO;

import ru.myproject.first_project.validators.DTOValidators.ResetPasswordDTOValid;
import ru.myproject.first_project.validators.DTOValidators.ValidResetPasswordToken;
import ru.myproject.first_project.validators.DTOValidators.ValidVerificationToken;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@ResetPasswordDTOValid
public class ResetPasswordDTO {
    @Size(min = 1, max = 30)
    @NotEmpty
    private String password;

    @NotEmpty
    private String passwordConfirmation;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
