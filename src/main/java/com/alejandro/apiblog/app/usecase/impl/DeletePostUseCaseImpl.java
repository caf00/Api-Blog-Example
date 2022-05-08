package com.alejandro.apiblog.app.usecase.impl;

import com.alejandro.apiblog.app.domain.PostRepository;
import com.alejandro.apiblog.app.exception.ResourceNotFoundException;
import com.alejandro.apiblog.app.usecase.DeletePostUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePostUseCaseImpl implements DeletePostUseCase {

    private final PostRepository postRepository;

    @Autowired
    public DeletePostUseCaseImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void execute(Integer id) {
        boolean exist = postRepository.existsById(id);
        if(!exist) {
            throw new ResourceNotFoundException("Post with Id: "+id+" not found.");
        }
        postRepository.deleteById(id);
    }
}
