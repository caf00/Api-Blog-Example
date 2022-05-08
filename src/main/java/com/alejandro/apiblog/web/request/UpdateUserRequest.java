package com.alejandro.apiblog.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UpdateUserRequest implements Serializable {
    @NotNull(message = "Id must not be null")
    private Integer id;
    @NotNull(message = "Id must not be null")
    @NotBlank(message = "Id must not be empty")
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
