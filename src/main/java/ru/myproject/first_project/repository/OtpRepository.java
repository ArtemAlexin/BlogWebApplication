package ru.myproject.first_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.myproject.first_project.domain.Otp;
import ru.myproject.first_project.domain.User;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Otp findByUser(User user);
    @Modifying
    @Query("UPDATE Otp o set o.code = :code, o.isUsed=false where o.id = :id ")
    void updateOtpById(long id, String code);
    @Modifying
    @Query("UPDATE Otp o set o.isUsed = true where o.user = :user")
    void invalidate(User user);
}
