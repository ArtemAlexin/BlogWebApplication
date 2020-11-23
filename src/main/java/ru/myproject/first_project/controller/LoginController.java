package ru.myproject.first_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.myproject.first_project.domain.UserCredentials;
import ru.myproject.first_project.service.UserServiceInterface;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
//todo logining only enabled users, probably it can be easily done with spring security
@Controller
public class LoginController extends AbstractController {
    UserServiceInterface userService;
    final Validator loginUserValidator;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(loginUserValidator);
    }
    @Autowired
    public LoginController(UserServiceInterface userService, @Qualifier("validatorLoginUser") Validator loginUserValidator) {
        this.userService = userService;
        this.loginUserValidator = loginUserValidator;
    }
    @GetMapping("/check/{id}")
    @ResponseBody
    public Long getCheckPage(@PathVariable("id") Long id) {
        return id;
    }
    @GetMapping("/enter")
    public String getEnterPage(Model model){
        model.addAttribute("loginForm", new UserCredentials());
        return "enterPage";
    }


    @PostMapping("/enter")
    public String login(@ModelAttribute("loginForm") @Valid UserCredentials loginForm, BindingResult result,
                        HttpSession session) {
        return "redirect:/";
    }
}
