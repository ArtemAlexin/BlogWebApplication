package ru.myproject.first_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.myproject.first_project.domain.DTO.ResetPasswordDTO;
import ru.myproject.first_project.domain.DTO.ResetPasswordTokenDTO;
import ru.myproject.first_project.domain.ResetPasswordToken;
import ru.myproject.first_project.service.ResetUserTokenServiceInterface;
import ru.myproject.first_project.service.UserServiceInterface;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
public class ResetPasswordController {
    private final ResetUserTokenServiceInterface resetUserTokenService;
    private final UserServiceInterface userService;

    @Autowired
    public ResetPasswordController(ResetUserTokenServiceInterface resetUserTokenService, UserServiceInterface userService) {
        this.resetUserTokenService = resetUserTokenService;
        this.userService = userService;
    }
    private void addErrorMessages(BindingResult result, Model model) {
        model.addAttribute("errorMessage",
                result.getAllErrors().stream().map
                        (DefaultMessageSourceResolvable::getDefaultMessage).
                        collect(Collectors.toList()));
    }
    @GetMapping("/resetPassword")
    public String resetPassword(@ModelAttribute("token") @Valid
                                ResetPasswordTokenDTO resetPasswordTokenDTO,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            addErrorMessages(result, model);
            return "error/invalidToken";
        }
        model.addAttribute("resetPasswordForm", new ResetPasswordDTO());
        return "resetPasswordPage";
    }

    @PostMapping("/resetPassword")
    public String resetPasswordConfirmation(@ModelAttribute("token") @Valid
                                                        ResetPasswordTokenDTO token,
                                            BindingResult result1,
                                            @ModelAttribute("resetPasswordForm")
                                            @Valid ResetPasswordDTO resetPasswordForm,
                                            BindingResult result2,
                                            Model model) {
        if(result1.hasErrors()) {
            addErrorMessages(result1, model);
            return "error/invalidToken";
        }
        if (result2.hasErrors()) {
            return "resetPasswordPage";
        }
        ResetPasswordToken resetPasswordToken = resetUserTokenService.
                findByToken(token.getToken());
        userService.updatePassword(resetPasswordToken.getUser().getId(),
                resetPasswordForm.getPassword());
        resetUserTokenService.setInvalid(resetPasswordToken.getId());
        return "redirect:/";
    }
}
