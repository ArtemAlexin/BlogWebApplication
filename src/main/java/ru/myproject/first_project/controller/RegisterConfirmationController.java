package ru.myproject.first_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.myproject.first_project.domain.DTO.VerificationTokenDTO;
import ru.myproject.first_project.domain.VerificationToken;
import ru.myproject.first_project.service.UserService;
import ru.myproject.first_project.service.UserServiceInterface;
import ru.myproject.first_project.service.VerificationTokenServiceInterface;

import javax.validation.Valid;
//todo normal validation
//todo javascript messsages

@Controller
@Validated
public class RegisterConfirmationController extends AbstractController {
    public static final String REGISTRATION_CONFIRMATION = "/registerConfirm";
    public static final String USER_IS_ALREADY_VERIFIED = "User is already verified";
    private static final String INVALID_TOKEN = "Invalid token";
    private static final String AUTHENTICATION_MESSAGE_IS_EXPIRED = "Authentication message is expired";

    private final UserServiceInterface userService;
    private final VerificationTokenServiceInterface verificationTokenService;

    @Autowired
    public RegisterConfirmationController(UserService userService,
                                          VerificationTokenServiceInterface verificationTokenService) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
    }

    @GetMapping(REGISTRATION_CONFIRMATION)
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
