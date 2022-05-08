package com.alejandro.apiblog.app.usecase;

import com.alejandro.apiblog.app.usecase.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListPostUseCase {
    Page<PostDto> execute(Pageable pageable);
}
