package ru.myproject.first_project.domain;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.Map;

public enum EncryptedAlgorithm {
    BCRYPT, SCRYPT;
    private static final Map<Class<? extends PasswordEncoder>, EncryptedAlgorithm> ENCRYPTED_ALGORITHM_MAP =
            Map.of(
                    BCryptPasswordEncoder.class, BCRYPT,
                    SCryptPasswordEncoder.class, SCRYPT
            );
    public static<T extends PasswordEncoder> EncryptedAlgorithm
    getEncryptedAlgorithm(Class<T> clazz) {
        return ENCRYPTED_ALGORITHM_MAP.get(clazz);
    }
}
