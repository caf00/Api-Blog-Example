package com.alejandro.apiblog.app.usecase;

import com.alejandro.apiblog.app.usecase.dto.UserDto;

public interface FindUserByIdUseCase {
    UserDto execute(Integer id);
}
