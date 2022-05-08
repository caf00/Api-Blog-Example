package com.alejandro.apiblog.app.usecase.impl;

import com.alejandro.apiblog.app.domain.UserRepository;
import com.alejandro.apiblog.app.exception.ResourceNotFoundException;
import com.alejandro.apiblog.app.usecase.FindUserByIdUseCase;
import com.alejandro.apiblog.app.usecase.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class FindUserByIdUseCaseImpl implements FindUserByIdUseCase {

    private final UserRepository userRepository;

    public FindUserByIdUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto execute(Integer id) {
        return userRepository.findById(id)
                .map(u->UserDto.newInstance(u.getId(), u.getName()))
                .orElseThrow(() -> new ResourceNotFoundException("User with Id: " + id + " not found."));
    }
}
