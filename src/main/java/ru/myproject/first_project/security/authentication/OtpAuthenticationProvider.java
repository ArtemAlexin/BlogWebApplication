package ru.myproject.first_project.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.myproject.first_project.domain.Otp;
import ru.myproject.first_project.service.OtpService;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final OtpService otpService;
    @Autowired
    public OtpAuthenticationProvider(@Qualifier("customUserDetailsService")
                                             UserDetailsService userDetailsService, OtpService otpService) {
        this.userDetailsService = userDetailsService;
        this.otpService = otpService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String code = (String)authentication.getCredentials();
        if(check(username, code)) {
            return new OtpAuthentication(username, code);
        }
        throw new BadCredentialsException("Invalid code");
    }

    private boolean check(String username, String code) {
        CustomUserDetails userDetails = (CustomUserDetails)userDetailsService.loadUserByUsername(username);
        if(userDetails == null) {
            return false;
        }
        Otp otp =  otpService.findByUser(userDetails.getUser());
        if(code != null &&
                code.equals(otp.getCode()) && !otp.isUsed()) {
            otpService.invalidate(otp.getUser());
            return true;
        }
        return false;
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.isAssignableFrom(authentication);
    }
}
