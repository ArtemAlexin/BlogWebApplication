package ru.myproject.first_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.myproject.first_project.domain.Otp;
import ru.myproject.first_project.domain.User;
import ru.myproject.first_project.repository.OtpRepository;
@Service
public class OtpServiceImpl implements OtpService {
    private final OtpRepository otpRepository;
    @Autowired
    public OtpServiceImpl(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    @Override
    public Otp findByUser(User user) {
        return otpRepository.findByUser(user);
    }

    @Override
    public void updateOtpById(long id, String newCode) {
        otpRepository.updateOtpById(id, newCode);
    }

    @Override
    @Transactional
    public void updateOrSave(User user, String code) {
        var otpToUpdate = otpRepository.findByUser(user);
        if(otpToUpdate == null) {
            Otp toSave = new Otp();
            toSave.setCode(code);
            toSave.setUser(user);
            otpRepository.save(toSave);
        } else {
            otpRepository.updateOtpById(otpToUpdate.getId(), code);
        }
    }

    @Override
    @Transactional
    public void invalidate(User user) {
        otpRepository.invalidate(user);
    }
}
