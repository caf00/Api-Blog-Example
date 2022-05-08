package com.alejandro.apiblog.app.usecase.impl;

import com.alejandro.apiblog.app.domain.PostRepository;
import com.alejandro.apiblog.app.usecase.dto.PostDto;
import com.alejandro.apiblog.app.usecase.dto.UserDto;
import com.alejandro.apiblog.app.usecase.ListPostUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListPostUseCaseImpl implements ListPostUseCase {

    private final PostRepository postRepository;

    @Autowired
    public ListPostUseCaseImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Page<PostDto> execute(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(p-> PostDto.newInstance(
                        p.getId(),
                        p.getTitle(),
                        p.getBody(),
                        p.getCreateDate(),
                        UserDto.newInstance(p.getUser().getId(), p.getUser().getName())
                ));
    }
}
