package com.alejandro.apiblog.app.usecase;

import com.alejandro.apiblog.app.usecase.dto.PostDto;

public interface FindPostByIdUdeCase {
    PostDto execute(Integer id);
}
