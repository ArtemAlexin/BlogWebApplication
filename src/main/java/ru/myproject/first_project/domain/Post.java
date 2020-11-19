package ru.myproject.first_project.domain;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @NotEmpty
    private String title;

    @ManyToOne
    private User author;

    @NotEmpty
    @NonNull
    private String text;

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    @NonNull
    public String getText() {
        return text;
    }
}
