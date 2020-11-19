package ru.myproject.first_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.myproject.first_project.domain.DTO.ForgotPasswordDTO;
import ru.myproject.first_project.events.ForgotPasswordEvent;
import ru.myproject.first_project.service.ResetUserTokenServiceInterface;
import ru.myproject.first_project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

//todo fix validation, make new DTO objects to make validation clear
@Controller
public class ForgotPasswordController extends AbstractController {
    private final UserService userService;
    private final ResetUserTokenServiceInterface resetUserTokenServiceInterface;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public ForgotPasswordController(UserService userService, ResetUserTokenServiceInterface resetUserTokenServiceInterface, ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.resetUserTokenServiceInterface = resetUserTokenServiceInterface;
        this.eventPublisher = eventPublisher;
    }

    @GetMapping("/forgotPassword")
    public String getResetPasswordPage(Model model) {
        model.addAttribute("forgotPasswordForm", new ForgotPasswordDTO());
        return "forgotPasswordPage";
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
