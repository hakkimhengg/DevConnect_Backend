package com.kshrd.devconnect_springboot.model.dto.request;

import java.util.Date;
import java.util.List;

import com.kshrd.devconnect_springboot.model.entity.TestCase;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "input is required")
    private List<TestCase> testCase;
    @NotBlank(message = "problem detail is required")
    private String problemDetail;
    @NotBlank(message = "score is required")
    private Integer score;
    @NotBlank(message = "starter code is required")
    private String starterCode;
    @NotBlank(message = "language is required")
    private String language;
    @NotBlank(message = "create at is required")
    private Date createdAt;
}
