package com.alejandro.apiblog.app.usecase.impl;

import com.alejandro.apiblog.app.domain.UserRepository;
import com.alejandro.apiblog.app.exception.ResourceNotFoundException;
import com.alejandro.apiblog.app.usecase.DeleteUserUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;

    public DeleteUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(Integer id) {
        boolean exist = userRepository.existsById(id);
        if(!exist) {
            throw new ResourceNotFoundException("User with Id: "+id+" not found.");
        }
        userRepository.deleteById(id);
    }
}
