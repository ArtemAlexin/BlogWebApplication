package ru.myproject.first_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.myproject.first_project.domain.ResetPasswordToken;
import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.repository.ResetUserTokenRepository;

import javax.transaction.Transactional;

@Service
public class ResetUserTokenService implements ResetUserTokenServiceInterface{
    ResetUserTokenRepository resetUserTokenRepository;
    @Autowired
    public ResetUserTokenService(ResetUserTokenRepository resetUserTokenRepository) {
        this.resetUserTokenRepository = resetUserTokenRepository;
    }

    @Override
    public ResetPasswordToken save(ResetPasswordToken token) {
        return resetUserTokenRepository.save(token);
    }

    @Override
    public ResetPasswordToken findByUser(User user) {
        return resetUserTokenRepository.findByUser(user);
    }

    @Override
    public ResetPasswordToken findByToken(String token) {
        return resetUserTokenRepository.findByToken(token);
    }

    @Override
    @Transactional
    public void updateToken(User user, String token) {
        resetUserTokenRepository.updateToken(user, token);
    }

    @Override
    @Transactional
    public void setInvalid(long id) {
        resetUserTokenRepository.setInvalid(id);
    }

    @Override
    public void setInvalid(String token) {
        resetUserTokenRepository.setInvalid(token);
    }
}
