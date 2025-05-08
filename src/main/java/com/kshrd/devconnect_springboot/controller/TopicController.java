package com.kshrd.devconnect_springboot.controller;


import com.kshrd.devconnect_springboot.base.ApiResponse;
import com.kshrd.devconnect_springboot.base.BaseController;
import com.kshrd.devconnect_springboot.model.dto.request.TopicRequest;
import com.kshrd.devconnect_springboot.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/topics")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TopicController extends BaseController {

    private final TopicService topicsService;

    @GetMapping
    public ResponseEntity<ApiResponse>  getAllTopics() {
        return response(ApiResponse.builder()
                .success(true)
                .message("Topics retrieved successfully")
                .status(HttpStatus.OK)
                .payload(topicsService.getAllTopics())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTopicsById(@PathVariable UUID id) {
        return response(ApiResponse.builder()
                .success(true)
                .message("Topics retrieved by id successfully")
                .status(HttpStatus.OK)
                .payload(topicsService.getTopicsById(id))
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createTopics(@RequestBody TopicRequest entity) {
        return response(ApiResponse.builder()
                .success(true)
                .message("Topics have been created successfully")
                .status(HttpStatus.CREATED)
                .payload(topicsService.createTopics(entity))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTopics(@PathVariable UUID id, @RequestBody TopicRequest entity) {
        return response(ApiResponse.builder()
                .success(true)
                .message("Topics have been updated successfully")
                .status(HttpStatus.OK)
                .payload(topicsService.updateTopics(id,entity))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTopics(@PathVariable UUID id) {
        topicsService.deleteTopics(id);
        return response(ApiResponse.builder()
                .success(true)
                .message("Topics have been deleted successfully")
                .status(HttpStatus.OK)
                .build());
    }
}
