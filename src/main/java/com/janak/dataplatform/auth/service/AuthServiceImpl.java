package com.janak.dataplatform.auth.service;

import com.janak.dataplatform.auth.dto.LoginRequest;
import com.janak.dataplatform.auth.dto.LoginResponse;
import com.janak.dataplatform.auth.dto.RegisterRequest;
import com.janak.dataplatform.security.jwt.JwtService;
import com.janak.dataplatform.user.entity.User;
import com.janak.dataplatform.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();

        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(
                passwordEncoder.encode(request.password())
        );
        user.setRole(request.role());
        user.setEnabled(true);

        userRepository.save(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() ->
                        new RuntimeException("Invalid credentials")
                );

        if (!user.getEnabled()) {
            throw new RuntimeException("Account is disabled");
        }

        if (!passwordEncoder.matches(
                request.password(),
                user.getPassword()
        )) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                token,
                "Bearer",
                jwtService.getExpiration(),
                Instant.now(),
                new LoginResponse.UserInfo(
                        user.getId().toString(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getRole(),
                        user.getEnabled()
                )
        );
    }
}