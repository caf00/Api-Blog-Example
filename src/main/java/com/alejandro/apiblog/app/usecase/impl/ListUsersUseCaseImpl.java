package com.alejandro.apiblog.app.usecase.impl;

import com.alejandro.apiblog.app.domain.UserRepository;
import com.alejandro.apiblog.app.usecase.dto.UserDto;
import com.alejandro.apiblog.app.usecase.ListUsersUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListUsersUseCaseImpl implements ListUsersUseCase {

    private final UserRepository userRepository;

    @Autowired
    public ListUsersUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserDto> execute(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(u->UserDto.newInstance(u.getId(), u.getName()));
    }
}
