package ru.myproject.first_project.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.repository.UserRepository;
import ru.myproject.first_project.security.authentication.CustomUserDetails;

import java.util.Objects;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(loginOrEmail);
        if(Objects.isNull(user)) {
            user = userRepository.findByEmail(loginOrEmail);
        }
        return new CustomUserDetails(
                Optional.ofNullable(user).orElseThrow(
                        () -> new UsernameNotFoundException("Invalid login or email")));
    }
}
