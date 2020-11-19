package ru.myproject.first_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myproject.first_project.domain.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
