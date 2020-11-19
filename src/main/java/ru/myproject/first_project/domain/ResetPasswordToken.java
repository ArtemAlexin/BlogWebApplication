package ru.myproject.first_project.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(
        indexes = {@Index(columnList = "user_id", unique = true),
                   @Index(columnList = "token", unique = true)
        }
)
//toDO
// check expiry time, deleting expiry data from the database after a day
// if it is done, check time of token creation won't be necessary
public class ResetPasswordToken {
    //24 hours
    private static final int EXPIRY_TIME_MINUTES = 60 * 24;

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @NotEmpty
    private String token;

    private boolean isValid;

    public ResetPasswordToken() {
        super();
        isValid = true;
    }

    public boolean isValid() {
        return isValid;
    }

    @CreationTimestamp
    private Date creationTime;

    public Date getCreationTime() {
        return creationTime;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
