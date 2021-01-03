package ru.myproject.first_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.myproject.first_project.domain.DTO.RegisterUserDTO;
import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.events.OnRegisterEvent;
import ru.myproject.first_project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegisterController extends AbstractController {
    private final UserService userService;
    private final ApplicationEventPublisher eventPublisher;


    private final Validator uniqueUserFieldsValidator;

    @Autowired
    public RegisterController(UserService userService, ApplicationEventPublisher eventPublisher,
                              @Qualifier("validatorUniqueUserFields") Validator uniqueUserFieldsValidator) {
        this.userService = userService;
        this.eventPublisher = eventPublisher;
        this.uniqueUserFieldsValidator = uniqueUserFieldsValidator;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(uniqueUserFieldsValidator);
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("registerForm") @Valid RegisterUserDTO user,
                           BindingResult result,
                           HttpServletRequest request) {
        if (result.hasErrors()) {
            return "registerPage";
        }
        User entityUser = new User();
        entityUser.setEmail(user.getEmail());
        entityUser.setPassword(user.getPassword());
        entityUser.setName(user.getName());
        entityUser.setLogin(user.getLogin());
        entityUser.setSurname(user.getSurname());
        User savedUser = userService.save(entityUser);
        eventPublisher.publishEvent(new OnRegisterEvent(savedUser, getAppURI(request)));
        return "redirect:/";
    }
}
