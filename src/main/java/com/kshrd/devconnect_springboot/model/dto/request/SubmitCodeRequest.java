package com.kshrd.devconnect_springboot.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmitCodeRequest {
    @NotBlank(message = "code is required")
    private String code;
    private Long timeSubmitted;
}
