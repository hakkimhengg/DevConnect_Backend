package com.kshrd.devconnect_springboot.controller;

import com.kshrd.devconnect_springboot.base.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/position")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PositionController {
    @GetMapping
    public ResponseEntity<ApiResponse> getAllPositions() {
        return null;
    }

    @GetMapping("/{position-id}")
    public ResponseEntity<ApiResponse> getPositionById(@PathVariable("position-id") UUID positionId) {
        return null;
    }
}
