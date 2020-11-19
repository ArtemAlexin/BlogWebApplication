package ru.myproject.first_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void setEnabled(long id) {
        userRepository.setEnabled(id);
    }

    @Override
    @Transactional
    public void updatePassword(long id, String password) {
        String passwordSha = passwordEncoder.encode(password);
        userRepository.updatePasswordSha(id, passwordSha);
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setPasswordSha(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByLoginOrEmailAndPassword(String loginOrEmail, String password) {
        User user = userRepository.findByEmail(loginOrEmail);
        if ((Objects.nonNull(user) ||
                Objects.nonNull(user = userRepository.findByLogin(loginOrEmail))) &&
                passwordEncoder.matches(password, user.getPasswordSha())) {
            return user;
        }
        return null;
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
