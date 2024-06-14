package com.finanzas.breadcredit.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.OffsetDateTime;

@Data
public class ErrorResponse {
    private Integer status;
    private HttpStatus error;
    private OffsetDateTime timestamp;
    private String trace;

    private ErrorResponse() {
        timestamp = OffsetDateTime.now();
    }

    ErrorResponse(HttpStatus status, Throwable ex) {
        this();
        this.status = status.value();
        this.error = status;
        this.trace = ex.getMessage();
    }
}
