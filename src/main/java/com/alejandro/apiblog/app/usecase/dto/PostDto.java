package com.alejandro.apiblog.app.usecase.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class PostDto implements Serializable {
    private Integer id;
    private String title;
    private String body;
    private LocalDate createDate;
    private UserDto userDto;

    public PostDto(Integer id,
                    String title,
                    String body,
                    LocalDate createDate,
                    UserDto userDto) {
        this.setId(id);
        this.setTitle(title);
        this.setBody(body);
        this.setCreateDate(createDate);
        this.setUserDto(userDto);
    }

    public PostDto(String title,
                    String body,
                    LocalDate createDate,
                    UserDto userDto) {
        this.setTitle(title);
        this.setBody(body);
        this.setCreateDate(createDate);
        this.setUserDto(userDto);
    }

    public static PostDto newInstance(Integer id,
                                      String title,
                                      String body,
                                      LocalDate createDate,
                                      UserDto userDto) {
        return new PostDto(id, title, body, createDate, userDto);
    }

    public static PostDto newInstance(String title,
                                      String body,
                                      LocalDate createDate,
                                      UserDto userDto) {
        return new PostDto(title, body, createDate, userDto);
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

    public UserDto getUserDto() {
        return userDto;
    }

    private void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Integer getUserDtoId() {
        return this.userDto.getId();
    }

    public String getUserDtoName() {
        return this.userDto.getName();
    }
}
