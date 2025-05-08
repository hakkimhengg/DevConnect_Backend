package com.kshrd.devconnect_springboot.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HackathonRequest {

    @NotBlank(message = "Hackathon title cannot be blank")
    private String hackathonTitle;

    @NotBlank(message = "Hackathon description cannot be blank")
    private String hackathonDescription;

    @NotNull(message = "Start date cannot be null")
    private LocalDateTime startDate;

    @NotNull(message = "End date cannot be null")
    private LocalDateTime endDate;

    @NotNull(message = "Created date cannot be null")
    private LocalDateTime createdDate = LocalDateTime.now();

    @NotNull(message = "Availability status cannot be null")
    private Boolean isAvailable;

    @NotNull(message = "Creator ID cannot be null")
    private Long creatorId; // Recruiter Id
}