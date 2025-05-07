package com.kshrd.devconnect_springboot.model.dto.request;

import java.time.LocalDateTime;

import java.util.List;

import com.kshrd.devconnect_springboot.model.templeJsonb.TestCase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeChallengeRequest {
    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message = "description is required")
    private String description;
    @NotNull(message = "time limit is required")
    private List<TestCase> testCase;
    @NotBlank(message = "problem detail is required")
    private String problemDetail;
    @NotNull(message = "time limit is required")
    private Integer score;
    @NotBlank(message = "starter code is required")
    private String starterCode;
    private LocalDateTime createdAt;
    @NotBlank(message = "language is required")
    private String language;
}
