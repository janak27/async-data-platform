package com.janak.dataplatform.auth.dto;

import java.time.Instant;

public record LoginResponse(

        String accessToken,
        String tokenType,
        long expiresIn,
        Instant issuedAt,
        UserInfo user

) {

    public record UserInfo(

            String id,
            String username,
            String email,
            String role,
            Boolean enabled

    ) {
    }
}