package com.janak.dataplatform.user.controller;

import com.janak.dataplatform.user.dto.CreateUserRequest;
import com.janak.dataplatform.user.dto.UserResponse;
import com.janak.dataplatform.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    static {
        System.out.println("UserController Loaded");
    }

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(
            @RequestBody CreateUserRequest request
    ) {
        return userService.createUser(request);
    }

    @GetMapping("/{id}")
    public UserResponse getUser(
            @PathVariable UUID id
    ) {
        return userService.getUser(id);
    }
}