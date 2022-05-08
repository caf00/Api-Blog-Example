package com.alejandro.apiblog.app.usecase.impl;

import com.alejandro.apiblog.app.domain.PostRepository;
import com.alejandro.apiblog.app.domain.UserRepository;
import com.alejandro.apiblog.app.domain.entity.Post;
import com.alejandro.apiblog.app.domain.entity.User;
import com.alejandro.apiblog.app.exception.InvalidArgumentException;
import com.alejandro.apiblog.app.usecase.dto.PostDto;
import com.alejandro.apiblog.app.exception.ResourceNotFoundException;
import com.alejandro.apiblog.app.usecase.CreatePostUseCase;
import com.alejandro.apiblog.app.usecase.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePostUseCaseImpl implements CreatePostUseCase {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CreatePostUseCaseImpl(PostRepository postRepository,
                                 UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void execute(PostDto postDto) {
        Long exist = postRepository.countByTitle(postDto.getTitle());
        if(exist>0) {
            throw new InvalidArgumentException("Title post: "+postDto.getTitle()+" already exist.");
        }
        UserDto userDto = postDto.getUserDto();
        if(userDto.getId()==null) {
            throw new InvalidArgumentException("User must not be null.");
        }
        User user = userRepository.findById(postDto.getUserDto().getId())
                        .orElseThrow(()-> new ResourceNotFoundException("User with id: "+postDto.getUserDto().getId()+" not found."));
        postRepository.save(Post.newInstance(
                postDto.getTitle(),
                postDto.getBody(),
                postDto.getCreateDate(),
                user
        ));
    }
}
