package com.alejandro.apiblog.app.usecase.impl;

import com.alejandro.apiblog.app.domain.PostRepository;
import com.alejandro.apiblog.app.exception.ResourceNotFoundException;
import com.alejandro.apiblog.app.usecase.FindPostByIdUdeCase;
import com.alejandro.apiblog.app.usecase.dto.PostDto;
import com.alejandro.apiblog.app.usecase.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindPostByIdUseCaseImpl implements FindPostByIdUdeCase {

    private final PostRepository postRepository;

    @Autowired
    public FindPostByIdUseCaseImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto execute(Integer id) {
        return postRepository.findById(id)
                .map(p->PostDto.newInstance(
                        p.getId(),
                        p.getTitle(),
                        p.getBody(),
                        p.getCreateDate(),
                        UserDto.newInstance(
                                p.getUserId(),
                                p.getUserName()
                        )
                ))
                .orElseThrow(()->new ResourceNotFoundException("Post with Id: "+id+" not found."));
    }
}
