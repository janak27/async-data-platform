
package com.janak.dataplatform.common.exception;

import java.time.Instant;
import java.util.Map;

public record ApiError(

        Instant timestamp,

        int status,

        String error,

        String path,

        Map<String, String> validationErrors

) {
}