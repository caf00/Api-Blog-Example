package com.alejandro.apiblog.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CreatePostRequest implements Serializable {
    @NotNull(message = "must not be null")
    @NotBlank(message = "must not be empty")
    private String title;
    @NotNull(message = "must not be null")
    @NotBlank(message = "must not be empty")
    private String body;
    @NotNull(message = "must not be null")
    private Integer userId;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Integer getUserId() {
        return userId;
    }
}
