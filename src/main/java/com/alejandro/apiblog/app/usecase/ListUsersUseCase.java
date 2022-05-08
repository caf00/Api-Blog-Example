package com.alejandro.apiblog.app.usecase;

import com.alejandro.apiblog.app.usecase.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListUsersUseCase {
    Page<UserDto> execute(Pageable pageable);
}
