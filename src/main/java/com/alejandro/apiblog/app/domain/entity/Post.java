package com.alejandro.apiblog.app.domain.entity;

import com.alejandro.apiblog.app.exception.InvalidArgumentException;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(nullable = false)
    private String body;
    private LocalDate createDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected Post() {
    }

    public Post(String title, String body, LocalDate createDate, User user) {
        this.setTitle(title);
        this.setBody(body);
        this.setCreateDate(createDate);
        this.setUser(user);
    }

    public Post(Integer id, String title, String body, LocalDate createDate, User user) {
        this.setId(id);
        this.setTitle(title);
        this.setBody(body);
        this.setCreateDate(createDate);
        this.setUser(user);
    }

    public static Post newInstance(Integer id,
                                   String title,
                                   String body,
                                   LocalDate createDate,
                                   User user) {
        return new Post(id, title, body, createDate, user);
    }

    public static Post newInstance(String title,
                                   String body,
                                   LocalDate createDate,
                                   User user) {
        return new Post(title, body, createDate, user);
    }

    private void setId(Integer id) {
        this.id = id;
    }

    private void setTitle(String title) {
        if(title==null) {
            throw new InvalidArgumentException("Post title must not be null.");
        }
        this.title = title;
    }

    private void setBody(String body) {
        if(body==null) {
            throw new InvalidArgumentException("Post body must not be null.");
        }
        this.body = body;
    }

    private void setUser(User user) {
        if(user==null) {
            throw new InvalidArgumentException("User must not be null.");
        }
        this.user = user;
    }

    private void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public User getUser() {
        return user;
    }

    public Integer getUserId() {
        return this.user.getId();
    }

    public String getUserName() {
        return this.user.getName();
    }
}
