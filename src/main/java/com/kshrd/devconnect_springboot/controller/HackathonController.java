package com.kshrd.devconnect_springboot.controller;

import com.kshrd.devconnect_springboot.base.ApiResponse;
import com.kshrd.devconnect_springboot.base.BaseController;
import com.kshrd.devconnect_springboot.model.dto.request.HackathonRequest;
import com.kshrd.devconnect_springboot.service.HackathonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/hackathons")
public class HackathonController extends BaseController {
    private final HackathonService hackathonService;

    @GetMapping
    @Operation(summary = "Get all hackathons")
    public ResponseEntity<ApiResponse> getAllHackathons(
            @RequestParam(defaultValue = "1") Long page,
            @RequestParam(defaultValue = "5") Long size) {
        return response(ApiResponse.builder()
                .success(true)
                .message("All Hackathons fetched successfully")
                .status(HttpStatus.OK)
                .payload(hackathonService.getAllHackathons(page, size))
                .build());
    }

    @GetMapping("/{hackathon_id}")
    @Operation(summary = "Get a hackathon by ID")
    public ResponseEntity<ApiResponse> getHackathonById(@PathVariable("hackathon_id") UUID hackathonId) {
        return response(ApiResponse.builder()
                .success(true)
                .message("Hackathon ID " + hackathonId + " Founded")
                .status(HttpStatus.OK)
                .payload(hackathonService.getHackathonById(hackathonId))
                .build());
    }

    @PutMapping("/{hackathon_id}")
    @Operation(summary = "Update a hackathon by ID")
    public ResponseEntity<ApiResponse> updateHackathonById(@PathVariable("hackathon_id") UUID hackathonId, @RequestBody HackathonRequest request) {
        return response(ApiResponse.builder()
                .success(true)
                .message("Hackathon ID " + hackathonId + " Founded")
                .status(HttpStatus.OK)
                .payload(hackathonService.updateHackathonById(hackathonId, request))
                .build());
    }

    @PostMapping
    @Operation(summary = "Create a hackathon")
    public ResponseEntity<ApiResponse> createHackathon(@RequestBody HackathonRequest request){
        return response(ApiResponse.builder()
                .success(true)
                .message("A Hackathon created successfully")
                .status(HttpStatus.OK)
                .payload(hackathonService.createHackathon(request))
                .build());
    }

    @DeleteMapping("/{hackathon_id}")
    @Operation(summary = "Delete a hackathon by ID")
    public void deleteHackathonById(@PathVariable("hackathon_id") UUID hackathonId){
        hackathonService.deleteHackathonById(hackathonId);
    }
}
