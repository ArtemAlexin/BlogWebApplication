package ru.myproject.first_project.controller;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractController {
    String getAppURI(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" +
                request.getServerPort() + request.getContextPath();
    }
}
