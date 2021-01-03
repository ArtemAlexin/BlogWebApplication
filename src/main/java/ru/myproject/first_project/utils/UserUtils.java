package ru.myproject.first_project.utils;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import ru.myproject.first_project.domain.User;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public final class UserUtils {
    private UserUtils() {

    }
    public static final String EMAIL_EXISTS_MESSAGE = "Email already exists";
    public static final String LOGIN_EXISTS_MESSAGE = "Login already exists";
    public static String generateOptCode() {
        return String.valueOf((int)(Math.random() * 9000 + 1000));
    }
}
