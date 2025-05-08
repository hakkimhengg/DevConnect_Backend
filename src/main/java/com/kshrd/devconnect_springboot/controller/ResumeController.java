package com.kshrd.devconnect_springboot.controller;

import com.kshrd.devconnect_springboot.base.ApiResponse;
import com.kshrd.devconnect_springboot.base.BaseController;
import com.kshrd.devconnect_springboot.model.dto.request.ResumeRequest;
import com.kshrd.devconnect_springboot.service.ResumeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ResumeController extends BaseController {
    private final ResumeService resumesService;
    @GetMapping
    public ResponseEntity<ApiResponse> getAllResumes() {
        return response(ApiResponse.builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Resumes retrieved successfully")
                .payload(resumesService.getAllResumes())
                .build());
        }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getResumesById(@PathVariable UUID id) {
        return response(ApiResponse.builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Resumes retrieved by id successfully")
                .payload(resumesService.getResumesById(id))
                .build());
    }
    @PostMapping
    public ResponseEntity<ApiResponse> createResumes(@RequestBody ResumeRequest entity) {
        return response(ApiResponse.builder()
                .success(true)
                .status(HttpStatus.CREATED)
                .message("Resumes created successfully")
                .payload(resumesService.createResumes(entity))
                .build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateResumes(@PathVariable UUID id, @RequestBody ResumeRequest entity) {
       return response(ApiResponse.builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Resumes updated successfully")
                .payload(resumesService.updateResumes(id, entity))
                .build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteResumes(@PathVariable UUID id) {
       return response(ApiResponse.builder()
                .success(true)
                .status(HttpStatus.OK)
                .message("Resumes deleted successfully")
                .payload(resumesService.deleteResumes(id))
                .build());
    }
}
