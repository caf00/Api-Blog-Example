package com.alejandro.apiblog.app.usecase.impl;

import com.alejandro.apiblog.app.usecase.dto.UserDto;

public interface UpdateUserUseCase {
    UserDto execute(UserDto userDto);
}
