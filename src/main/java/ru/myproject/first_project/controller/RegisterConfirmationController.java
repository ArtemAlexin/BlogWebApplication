package ru.myproject.first_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.myproject.first_project.domain.DTO.VerificationTokenDTO;
import ru.myproject.first_project.domain.VerificationToken;
import ru.myproject.first_project.service.UserServiceImpl;
import ru.myproject.first_project.service.UserService;
import ru.myproject.first_project.service.VerificationTokenService;

import javax.validation.Valid;
//todo normal validation
@Controller
@Validated
public class RegisterConfirmationController extends AbstractController {
    private final UserService userService;
    private final VerificationTokenService verificationTokenService;

    @Autowired
    public RegisterConfirmationController(UserServiceImpl userService,
                                          VerificationTokenService verificationTokenService) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
    }

    @GetMapping("/registerConfirm")
    public String registerConfirmation(@ModelAttribute("token") @Valid VerificationTokenDTO token,
                                       BindingResult result) {
        if(result.hasErrors()) {
            return "error/invalidToken";
        }
        VerificationToken verificationToken = verificationTokenService.findByToken(token.getToken());
        userService.setEnabled(verificationToken.getUser().getId());
        return "redirect:/index";
    }
}
