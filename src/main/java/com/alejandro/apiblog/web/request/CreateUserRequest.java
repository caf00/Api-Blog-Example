package com.alejandro.apiblog.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CreateUserRequest implements Serializable {
    @NotNull(message = "must not be null")
    @NotBlank(message = "must not be empty")
    @Size(min = 3, max = 20, message = "size must be between 3 and 20")
    private String name;

    public String getName() {
        return name;
    }
}
