package ru.myproject.first_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.myproject.first_project.domain.ResetPasswordToken;
import ru.myproject.first_project.domain.User;

@Repository
public interface ResetUserTokenRepository extends JpaRepository<ResetPasswordToken, Long> {
    ResetPasswordToken findByUser(User user);

    ResetPasswordToken findByToken(String token);

    @Modifying
    @Query("UPDATE ResetPasswordToken u set u.isValid=false where u.id=:id")
    void setInvalid(long id);
    @Modifying
    @Query("UPDATE ResetPasswordToken u set u.isValid=false where u.token=:token")
    void setInvalid(String token);
    @Modifying
    @Query("UPDATE ResetPasswordToken u set u.token=:token, u.isValid=true where u.user=:user")
    void updateToken(User user, String token);
}
