package ru.myproject.first_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController extends AbstractController {
    @GetMapping("/about")
    public String getAboutPage() {
        return "aboutController";
    }

}
