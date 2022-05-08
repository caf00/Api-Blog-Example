package com.alejandro.apiblog.app.usecase.dto;

public class UserDto {
    private Integer id;
    private String name;

    public UserDto(Integer id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public UserDto(String name) {
        this.setName(name);
    }

    public static UserDto newInstance(Integer id, String name) {
        return new UserDto(id, name);
    }

    public static UserDto newInstance(String name) {
        return new UserDto(name);
    }

    public static UserDto newFakeInstance(Integer id) {
        return new UserDto(id, null);
    }

    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
