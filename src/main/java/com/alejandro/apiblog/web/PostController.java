package com.alejandro.apiblog.web;

import com.alejandro.apiblog.app.usecase.DeletePostUseCase;
import com.alejandro.apiblog.app.usecase.FindPostByIdUdeCase;
import com.alejandro.apiblog.app.usecase.dto.PostDto;
import com.alejandro.apiblog.app.usecase.dto.UserDto;
import com.alejandro.apiblog.app.usecase.CreatePostUseCase;
import com.alejandro.apiblog.app.usecase.ListPostUseCase;
import com.alejandro.apiblog.web.request.CreatePostRequest;
import com.alejandro.apiblog.web.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final CreatePostUseCase createPostUseCase;
    private final ListPostUseCase listPostUseCase;
    private final FindPostByIdUdeCase findPostByIdUdeCase;
    private final DeletePostUseCase deletePostUseCase;

    @Autowired
    public PostController(CreatePostUseCase createPostUseCase,
                          ListPostUseCase listPostUseCase,
                          FindPostByIdUdeCase findPostByIdUdeCase,
                          DeletePostUseCase deletePostUseCase) {
        this.createPostUseCase = createPostUseCase;
        this.listPostUseCase = listPostUseCase;
        this.findPostByIdUdeCase = findPostByIdUdeCase;
        this.deletePostUseCase = deletePostUseCase;
    }

    @GetMapping
    public ResponseEntity<ListResponse<PostResponse>> getList() {
        Page<PostDto> pagePost = listPostUseCase.execute(Pageable.ofSize(20));
        List<PostResponse> posts = pagePost
                .getContent()
                .stream()
                .map(p -> PostResponse.newInstance(
                        p.getId(),
                        p.getTitle(),
                        p.getBody(),
                        p.getCreateDate(),
                        UserResponse.newInstance(p.getUserDto().getId(), p.getUserDto().getName())
                ))
                .collect(Collectors.toList());
        ListResponse<PostResponse> listResponse = ListResponse.newInstance(
                posts,
                pagePost.getNumber(),
                pagePost.getTotalPages(),
                pagePost.hasNext(),
                pagePost.hasPrevious()
        );
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleResponse<PostResponse>> findById(@PathVariable Integer id) {
        PostDto postDto = findPostByIdUdeCase.execute(id);
        SingleResponse<PostResponse> singleResponse = SingleResponse.newInstance(
                PostResponse.newInstance(
                        postDto.getId(),
                        postDto.getTitle(),
                        postDto.getBody(),
                        postDto.getCreateDate(),
                        UserResponse.newInstance(
                                postDto.getUserDtoId(),
                                postDto.getUserDtoName()
                        )
        ));
        return ResponseEntity.ok(singleResponse);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> create(@Valid @RequestBody CreatePostRequest createPostRequest) {
        createPostUseCase.execute(PostDto.newInstance(
                createPostRequest.getTitle(),
                createPostRequest.getBody(),
                LocalDate.now(),
                UserDto.newFakeInstance(createPostRequest.getUserId())
        ));
        return ResponseEntity.ok(MessageResponse.newInstance("Post created successfully."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Integer id) {
        deletePostUseCase.execute(id);
        return ResponseEntity.ok(MessageResponse.newInstance("Post with Id: "+id+" deleted successfully."));
    }
}
