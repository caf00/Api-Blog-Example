package com.alejandro.apiblog.web.response;

import java.time.LocalDate;

public class PostResponse {

    private Integer id;
    private String title;
    private String body;
    private LocalDate createDate;
    private UserResponse user;

    public PostResponse(Integer id,
                         String title,
                         String body,
                         LocalDate createDate,
                         UserResponse user) {
        this.setId(id);
        this.setTitle(title);
        this.setBody(body);
        this.setCreateDate(createDate);
        this.setUser(user);
    }

    public static PostResponse newInstance(Integer id,
                                           String title,
                                           String body,
                                           LocalDate createDate,
                                           UserResponse user) {
        return new PostResponse(id, title, body, createDate, user);
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    private void setBody(String body) {
        this.body = body;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    private void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public UserResponse getUser() {
        return user;
    }

    private void setUser(UserResponse userResponse) {
        this.user = userResponse;
    }
}
