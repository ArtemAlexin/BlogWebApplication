package ru.myproject.first_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.events.OnRegisterEvent;
import ru.myproject.first_project.service.UserServiceInterface;
import ru.myproject.first_project.utils.UserUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegisterController extends AbstractController {
    private final UserServiceInterface userService;
    private final ApplicationEventPublisher eventPublisher;


    private final Validator uniqueUserFieldsValidator;

    @Autowired
    public RegisterController(UserServiceInterface userService, ApplicationEventPublisher eventPublisher,
                              @Qualifier("validatorUniqueUserFields") Validator uniqueUserFieldsValidator) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.uniqueUserFieldsValidator = uniqueUserFieldsValidator;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(uniqueUserFieldsValidator);
    }

    @GetMapping("/register")
    public String getRegisterPage(HttpSession session, Model model) {
        model.addAttribute("registerForm", new User());
        return "registerPage";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerForm") @Valid User user,
                           BindingResult result,
                           HttpServletRequest request,
                           HttpSession session) {
        if (result.hasErrors()) {
            return "registerPage";
        }
        User savedUser = userService.save(user);
        eventPublisher.publishEvent(new OnRegisterEvent(savedUser, getAppURI(request)));
        UserUtils.setUser(session, savedUser);
        return "redirect:/";
    }
}
