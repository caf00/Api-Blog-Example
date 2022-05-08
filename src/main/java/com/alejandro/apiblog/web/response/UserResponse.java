package com.alejandro.apiblog.web.response;


public class UserResponse {

    private Integer id;
    private String name;

    public UserResponse(Integer id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public static UserResponse newInstance(Integer id, String name) {
        return new UserResponse(id, name);
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
