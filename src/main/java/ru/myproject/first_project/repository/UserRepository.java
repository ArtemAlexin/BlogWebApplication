package ru.myproject.first_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.myproject.first_project.domain.User;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLoginAndPassword(String login, String password);
    User findByEmail(String email);
    User findByLogin(String login);
    @Modifying
    @Query("UPDATE User u set u.isVerified=true where u.id = :id")
    void setEnabled(long id);
    @Modifying
    @Query("UPDATE User u set u.password = :password where u.id = :id")
    void updatePassword(long id, String password);
}
