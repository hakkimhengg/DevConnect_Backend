package com.kshrd.devconnect_springboot.controller;

import com.kshrd.devconnect_springboot.base.ApiResponse;
import com.kshrd.devconnect_springboot.base.BaseController;
import com.kshrd.devconnect_springboot.model.dto.request.CodeChallengeRequest;
import com.kshrd.devconnect_springboot.model.entity.CodeChallenge;
import com.kshrd.devconnect_springboot.service.CodeChallengeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.UUID;

@RestController
@RequestMapping("/api/codeChallenge")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class CodeChallengeController extends BaseController {

    private final CodeChallengeService codeChallengeService;
    @GetMapping
    public ResponseEntity<ApiResponse>  getAllCodeChallenge() {
        List<CodeChallenge> entity = codeChallengeService.getAllCodeChallenge();
        return response(ApiResponse.builder()
                .success(true)
                .message("CodeChallenge retrieved successfully")
                .status(HttpStatus.OK)
                .payload(entity)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCodeChallengeById(@PathVariable UUID id) {
        CodeChallenge entity = codeChallengeService.getCodeChallengeById(id);
        return response(ApiResponse.builder()
                .success(true)
                .message("CodeChallenge retrieved successfully")
                .status(HttpStatus.OK)
                .payload(entity)
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCodeChallenge(@RequestBody @Valid CodeChallengeRequest entity) {
        CodeChallenge service = codeChallengeService.createCodeChallenge(entity);
       return response(ApiResponse.builder()
                .success(true)
                .message("CodeChallenge created successfully")
                .status(HttpStatus.CREATED)
                .payload(service)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCodeChallenge(@PathVariable UUID id, @RequestBody @Valid CodeChallengeRequest entity) {
        CodeChallenge updatedEntity = codeChallengeService.updateCodeChallenge(id,entity);
        return response(ApiResponse.builder()
                .success(true)
                .message("CodeChallenge updated successfully")
                .status(HttpStatus.OK)
                .payload(updatedEntity)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCodeChallenge(@PathVariable UUID id) {
        CodeChallenge entity = codeChallengeService.deleteCodeChallenge(id);
        return response(ApiResponse.builder()
                .success(true)
                .message("CodeChallenge deleted successfully")
                .status(HttpStatus.OK)
                .payload(entity)
                .build());
    }

}
