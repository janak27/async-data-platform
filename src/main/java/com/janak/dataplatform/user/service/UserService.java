package com.janak.dataplatform.user.service;

import com.janak.dataplatform.user.dto.CreateUserRequest;
import com.janak.dataplatform.user.dto.UserResponse;

import java.util.UUID;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse getUser(UUID id);
}