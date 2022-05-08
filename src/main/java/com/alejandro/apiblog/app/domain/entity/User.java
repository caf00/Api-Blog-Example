package com.alejandro.apiblog.app.domain.entity;

import com.alejandro.apiblog.app.exception.InvalidArgumentException;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;

    protected User() {
    }

    public User(Integer id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public User(String name) {
        this.setName(name);
    }

    public static User newInstance(Integer id, String name) {
        return new User(id, name);
    }

    public static User newInstance(String name) {
        return new User(name);
    }

    public void updateFrom(String name) {
        this.setName(name);
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
        if(name==null||name.equals("")) {
            throw new InvalidArgumentException("User name must not be null");
        }
        this.name = name;
    }
}
