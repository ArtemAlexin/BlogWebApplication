package ru.myproject.first_project.utils;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import ru.myproject.first_project.domain.User;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public final class UserUtils {
    private UserUtils() {

    };
    public static final String EMAIL_EXISTS_MESSAGE = "Email already exists";
    public static final String LOGIN_EXISTS_MESSAGE = "Login already exists";

    public static User getUser(HttpSession session) {
        return (User)session.getAttribute("user");
    }
    public static boolean hasUser(HttpSession session) {
        return session.getAttribute("user") != null;
    }
    public static void setUser(HttpSession session, User user) {
        session.setAttribute("user", user);
    }
    public static void unsetUser(HttpSession session) {
        session.removeAttribute("user");
    }
}
