package ru.myproject.first_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BadUserController extends AbstractController {
    @GetMapping("/badUser")
    public String getBadUserPage() {
        return "error/invalidToken";
    }
}
