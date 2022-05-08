package com.alejandro.apiblog.web;

import com.alejandro.apiblog.app.usecase.DeleteUserUseCase;
import com.alejandro.apiblog.app.usecase.FindUserByIdUseCase;
import com.alejandro.apiblog.app.usecase.dto.UserDto;
import com.alejandro.apiblog.app.usecase.CreateUserUseCase;
import com.alejandro.apiblog.app.usecase.ListUsersUseCase;
import com.alejandro.apiblog.app.usecase.impl.UpdateUserUseCase;
import com.alejandro.apiblog.web.request.CreateUserRequest;
import com.alejandro.apiblog.web.request.UpdateUserRequest;
import com.alejandro.apiblog.web.response.ListResponse;
import com.alejandro.apiblog.web.response.MessageResponse;
import com.alejandro.apiblog.web.response.SingleResponse;
import com.alejandro.apiblog.web.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final ListUsersUseCase listUsersUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @Autowired
    public UserController(ListUsersUseCase listUsersUseCase,
                          CreateUserUseCase createUserUseCase,
                          FindUserByIdUseCase findUserByIdUseCase,
                          DeleteUserUseCase deleteUserUseCase,
                          UpdateUserUseCase updateUserUseCase) {
        this.listUsersUseCase = listUsersUseCase;
        this.createUserUseCase = createUserUseCase;
        this.findUserByIdUseCase = findUserByIdUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    @GetMapping
    public ResponseEntity<ListResponse<UserResponse>> getList() {
        Page<UserDto> pageUser = listUsersUseCase.execute(Pageable.ofSize(20));
        List<UserResponse> users = pageUser
                .getContent()
                .stream()
                .map(u-> UserResponse.newInstance(u.getId(), u.getName()))
                .collect(Collectors.toList());
        ListResponse<UserResponse> listResponse = ListResponse.newInstance(
                users,
                pageUser.getNumber(),
                pageUser.getTotalPages(),
                pageUser.hasNext(),
                pageUser.hasPrevious()
        );
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleResponse<UserResponse>> findById(@PathVariable Integer id) {
        UserDto userDto = findUserByIdUseCase.execute(id);
        SingleResponse<UserResponse> singleResponse = SingleResponse.newInstance(
                UserResponse.newInstance(userDto.getId(), userDto.getName())
        );
        return ResponseEntity.ok(singleResponse);
    }


    @PostMapping
    public ResponseEntity<MessageResponse> create(@Valid @RequestBody CreateUserRequest createUserRequest) {
        createUserUseCase.execute(UserDto.newInstance(createUserRequest.getName()));
        return ResponseEntity.ok(MessageResponse.newInstance("User created successfully."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Integer id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.ok(MessageResponse.newInstance("User with Id: "+id+" deleted successfully."));
    }

    @PutMapping
    public ResponseEntity<MessageResponse> update(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        updateUserUseCase.execute(UserDto.newInstance(
                updateUserRequest.getId(),
                updateUserRequest.getName()
        ));
        return ResponseEntity.ok(MessageResponse.newInstance("User updated successfully"));
    }

}
