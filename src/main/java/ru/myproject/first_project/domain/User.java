package ru.myproject.first_project.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;
import ru.myproject.first_project.validators.register.PasswordsMatches;
import ru.myproject.first_project.validators.ValidEmail;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

//todo create userDTO to prevent keeping null fields in object, password strength
@Entity
@Table(
        indexes = {
                @Index(columnList = "login", unique = true),
                @Index(columnList = "creationTime"),
                @Index(columnList = "email", unique = true)
        }
)
@PasswordsMatches
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String passwordSha;

    @Transient
    private String password;

    @NonNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String login;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @Transient
    private String passwordConfirmation;

    boolean isVerified = false;

    @NotEmpty
    @ValidEmail
    private String email;

    public String getPasswordSha() {
        return passwordSha;
    }

    public void setPasswordSha(String passwordSha) {
        this.passwordSha = passwordSha;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    @OneToMany
    private List<Post> posts;

    @CreationTimestamp
    private Date creationTime;

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public long getId() {
        return id;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isVerified() {
        return isVerified;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @NonNull
    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Date getCreationTime() {
        return creationTime;
    }
}
