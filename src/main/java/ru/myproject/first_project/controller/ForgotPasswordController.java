package ru.myproject.first_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.myproject.first_project.domain.DTO.ForgotPasswordDTO;
import ru.myproject.first_project.events.ForgotPasswordEvent;
import ru.myproject.first_project.service.ResetUserTokenService;
import ru.myproject.first_project.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//todo fix validation, make new DTO objects to make validation clear
@Controller
public class ForgotPasswordController extends AbstractController {
    private final UserServiceImpl userService;
    private final ResetUserTokenService resetUserTokenServiceInterface;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public ForgotPasswordController(UserServiceImpl userService, ResetUserTokenService resetUserTokenServiceInterface, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.resetUserTokenServiceInterface = resetUserTokenServiceInterface;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/forgotPassword")
    public String resetPassword(@ModelAttribute("forgotPasswordForm") @Valid
                                ForgotPasswordDTO passwordDTO,
                                BindingResult result,
                                HttpServletRequest request) {
        if (result.hasErrors()) {
            return "forgotPasswordPage";
        }
        eventPublisher.publishEvent(new ForgotPasswordEvent(userService.findByEmail(passwordDTO.getEmail()),
                getAppURI(request)));
        return "redirect:/";
    }
}
