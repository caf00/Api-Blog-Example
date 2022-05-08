package com.alejandro.apiblog.app.usecase.impl;

import com.alejandro.apiblog.app.domain.UserRepository;
import com.alejandro.apiblog.app.domain.entity.User;
import com.alejandro.apiblog.app.exception.ResourceNotFoundException;
import com.alejandro.apiblog.app.usecase.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase{

    private final UserRepository userRepository;

    @Autowired
    public UpdateUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto execute(UserDto userDto) {
        User userToUpdate = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User with Id: " + userDto.getId() + " not found"));
        userToUpdate.updateFrom(userDto.getName());
        User userUpdated = userRepository.save(userToUpdate);
        return UserDto.newInstance(userUpdated.getId(), userUpdated.getName());
    }
}
