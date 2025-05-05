package com.kshrd.devconnect_springboot.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse{
    private Boolean success;
    private String message;
    private HttpStatus status;
    private Object payload;
    private final LocalDateTime timestamp = LocalDateTime.now();
}