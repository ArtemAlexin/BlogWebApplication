package ru.myproject.first_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController extends AbstractController {
    @GetMapping({"/", "/index"})
    public String getIndexPage() {
        return "indexPage";
    }
}
