package com.finanzas.breadcredit.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ControllerError {
    private Integer status;
    private HttpStatus error;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime localDateTime;
    private String trace;

    private ControllerError() {
        localDateTime = LocalDateTime.now();
    }

    ControllerError(HttpStatus status, Throwable ex) {
        this();
        this.status = status.value();
        this.error = status;
        this.trace = ex.getMessage();
    }
}
