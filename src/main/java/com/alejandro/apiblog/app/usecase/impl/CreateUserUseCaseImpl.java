package com.alejandro.apiblog.app.usecase.impl;

import com.alejandro.apiblog.app.domain.UserRepository;
import com.alejandro.apiblog.app.domain.entity.User;
import com.alejandro.apiblog.app.usecase.dto.UserDto;
import com.alejandro.apiblog.app.usecase.CreateUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;

    @Autowired
    public CreateUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(UserDto userDto) {
        userRepository.save(User.newInstance(userDto.getName()));
    }
}
