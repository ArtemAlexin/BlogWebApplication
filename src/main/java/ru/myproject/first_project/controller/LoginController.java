package ru.myproject.first_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.myproject.first_project.domain.DTO.UserCodeDTO;
import ru.myproject.first_project.domain.DTO.UserDTO;

//todo logining only enabled users, probably it can be easily done with spring security
@Controller
public class LoginController extends AbstractController {
   @GetMapping("/login")
    public String getEnterPage(Model model){
        model.addAttribute("loginForm", new UserDTO());
        return "enterPage";
    }
    @GetMapping("/login/code")
    public String getEnterCodePage(Model model){
        model.addAttribute("loginCodeForm", new UserCodeDTO());
        return "enterPage";
    }
}
