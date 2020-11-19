package ru.myproject.first_project.service;

import org.springframework.stereotype.Service;
import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.domain.VerificationToken;
import ru.myproject.first_project.repository.VerificationTokenRepository;

import javax.transaction.Transactional;

@Service
public class VerificationTokenService implements VerificationTokenServiceInterface {
    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }


    @Override
    public VerificationToken registerVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        return verificationTokenRepository.save(verificationToken);
    }
}
