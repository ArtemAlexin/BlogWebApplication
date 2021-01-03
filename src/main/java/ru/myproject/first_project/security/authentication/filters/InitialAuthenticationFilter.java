package ru.myproject.first_project.security.authentication.filters;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.myproject.first_project.security.authentication.OtpAuthentication;
import ru.myproject.first_project.security.authentication.UsernamePasswordAuthentication;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class InitialAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private final AuthenticationManager authenticationManager;

    public InitialAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getRequestURI().equals("/login") || Objects.isNull(request.getParameter("username"))
                || (Objects.isNull(request.getParameter("password")) && Objects.isNull(request.getParameter("code")));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = String.valueOf(request.getParameter("username"));
        String password = String.valueOf(request.getParameter("password"));
        String code = request.getParameter("code");
        if(Objects.isNull(code)) {
            Authentication authentication = new UsernamePasswordAuthentication(username, password);
            authenticationManager.authenticate(authentication);
        } else {
            Authentication authentication = new OtpAuthentication(username, code);
            authenticationManager.authenticate(authentication);
            String jwt = Jwts.builder().
                    setClaims(Map.of("username", username)).signWith(KeysUtils.generateKey()).compact();
            response.setHeader("jwt", jwt);
        }
    }
}
