package com.alejandro.apiblog.app.usecase;

import com.alejandro.apiblog.app.usecase.dto.PostDto;

public interface CreatePostUseCase {
    void execute(PostDto postDto);
}
