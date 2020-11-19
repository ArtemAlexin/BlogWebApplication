package ru.myproject.first_project.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(
        indexes = @Index(columnList = "token", unique = true)
)
public class VerificationToken {
    // 24 hours
    private static final int EXPIRY_TIME_MINUTES = 60 * 24;
    @Id
    @GeneratedValue
    private long id;

    private String token;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @CreationTimestamp
    private Date creationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    private Date calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, EXPIRY_TIME_MINUTES);
        return calendar.getTime();
    }
    public boolean isValid() {
        return calculateExpiryDate().getTime() - getCreationTime().getTime() >= 0;
    }
}
