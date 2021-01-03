package ru.myproject.first_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.myproject.first_project.domain.DTO.UserDTO;
import ru.myproject.first_project.domain.EncryptedAlgorithm;
import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
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
        userRepository.updatePassword(id, passwordSha);
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEncryptedAlgorithm(EncryptedAlgorithm.getEncryptedAlgorithm(passwordEncoder.getClass()));
        return userRepository.save(user);
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
