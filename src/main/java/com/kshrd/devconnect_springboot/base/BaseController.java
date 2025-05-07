package com.kshrd.devconnect_springboot.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    protected ResponseEntity<ApiResponse> response() {
        return ResponseEntity.ok().build();
    }

    protected ResponseEntity<ApiResponse> response(ApiResponse apiResponse) {
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

}
