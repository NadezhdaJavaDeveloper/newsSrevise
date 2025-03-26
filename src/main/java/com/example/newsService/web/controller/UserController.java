package com.example.newsService.web.controller;

import com.example.newsService.mapper.UserMapper;
import com.example.newsService.model.User;
import com.example.newsService.service.UserService;
import com.example.newsService.web.model.RequestFilter;
import com.example.newsService.web.model.user.UpsertUserRequest;
import com.example.newsService.web.model.user.UserListResponse;
import com.example.newsService.web.model.user.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<UserListResponse> findAll(@Valid RequestFilter filter) {
        return ResponseEntity.ok(
                userMapper.userListToResponseList(
                        userService.findAll(filter)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(
                userMapper.userToResponse(userService.findById(id))
        );
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UpsertUserRequest request) {
        User newUser = userService.save(userMapper.requestToUser(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.userToResponse(newUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id,
                                               @RequestBody @Valid UpsertUserRequest request) {
        User updateUser = userService.update(userMapper.requestToUser(id, request));

        return ResponseEntity.ok(userMapper.userToResponse(updateUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
