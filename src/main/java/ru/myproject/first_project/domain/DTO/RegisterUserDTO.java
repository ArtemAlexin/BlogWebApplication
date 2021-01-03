package ru.myproject.first_project.domain.DTO;

import ru.myproject.first_project.domain.Post;
import ru.myproject.first_project.validators.DTOValidators.PasswordsMatches;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@PasswordsMatches
public class RegisterUserDTO {
    @NotEmpty
    @Size(min = 2, max = 30)
    private String login;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    private String email;

    private String password;

    private String passwordConfirmation;

    @OneToMany
    private List<Post> posts;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
