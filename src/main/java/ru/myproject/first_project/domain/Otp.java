package ru.myproject.first_project.domain;

import javax.persistence.*;

@Entity
@Table (
        indexes = @Index(columnList = "user_id", unique = true)
)
public class Otp {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(fetch = FetchType.EAGER)
    private User user;
    private String code;
    private boolean isUsed = false;

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
