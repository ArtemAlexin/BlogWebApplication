package ru.myproject.first_project.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.myproject.first_project.validators.DTOValidators.PasswordsMatches;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

//todo create userDTO to prevent keeping null fields in object, password strength
@Entity
@Table(
        indexes = {
                @Index(columnList = "login", unique = true),
                @Index(columnList = "creationTime"),
                @Index(columnList = "email", unique = true)
        }
)
public class User {
    @Id
    @GeneratedValue
    private long id;

    private String password;

    @NonNull
    @NotEmpty
    @Size(min = 2, max = 30)
    private String login;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    boolean isVerified = false;

    private String email;

    @OneToMany
    private List<Post> posts;

    @CreationTimestamp
    private Date creationTime;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Authority> authorities;
    @Enumerated(value = EnumType.STRING)
    private EncryptedAlgorithm encryptedAlgorithm;

    public EncryptedAlgorithm getEncryptedAlgorithm() {
        return encryptedAlgorithm;
    }

    public void setEncryptedAlgorithm(EncryptedAlgorithm encryptedAlgorithm) {
        this.encryptedAlgorithm = encryptedAlgorithm;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordSha) {
        this.password = passwordSha;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
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
    public List<GrantedAuthority> getGrantedAuthorities() {
        return getAuthorities().stream().map(
                x -> new SimpleGrantedAuthority(x.getName()))
                .collect(Collectors.toList());
    }
}
