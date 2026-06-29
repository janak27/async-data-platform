package com.janak.dataplatform.auth.service;
import com.janak.dataplatform.auth.dto.*;

public interface AuthService {

    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
