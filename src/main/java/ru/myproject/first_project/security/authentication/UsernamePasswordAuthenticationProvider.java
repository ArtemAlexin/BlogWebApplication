package ru.myproject.first_project.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.myproject.first_project.domain.Otp;
import ru.myproject.first_project.events.OtpEvent;
import ru.myproject.first_project.service.OtpService;
import ru.myproject.first_project.utils.UserUtils;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserDetailsService userDetailsService;
    private final OtpService otpService;
    @Autowired
    public UsernamePasswordAuthenticationProvider(PasswordEncoder passwordEncoder, ApplicationEventPublisher applicationEventPublisher,
                                                  @Qualifier("customUserDetailsService") UserDetailsService userDetailsService, OtpService otpService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
        this.userDetailsService = userDetailsService;
        this.otpService = otpService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginOrEmail = authentication.getName();
        String password = (String)authentication.getCredentials();
        UserDetails userDetails;
        if((userDetails = userDetailsService.loadUserByUsername(loginOrEmail)) != null) {
            if(passwordEncoder.matches(password, userDetails.getPassword())) {
                auth(userDetails);
                return new UsernamePasswordAuthentication(loginOrEmail, password);
            } else {
                throw new BadCredentialsException("Invalid password");
            }
        } else {
            throw new BadCredentialsException("Invalid login or email");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.isAssignableFrom(authentication);
    }

    private void auth(UserDetails user) {
        CustomUserDetails userDetails = (CustomUserDetails)user;
        String code = UserUtils.generateOptCode();
        applicationEventPublisher.publishEvent(new OtpEvent(userDetails.getUser(), code));
        otpService.updateOrSave(userDetails.getUser(), code);
    }
}
