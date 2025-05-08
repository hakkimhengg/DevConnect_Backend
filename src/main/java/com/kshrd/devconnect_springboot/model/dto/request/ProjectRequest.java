package com.kshrd.devconnect_springboot.model.dto.request;

import com.kshrd.devconnect_springboot.model.entity.Position;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequest {
    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Positive
    private Integer maxMember;

    @NotNull
    @FutureOrPresent
    private LocalDate createdAt;

    @NotNull
    private List<UUID> positions;

    @NotNull
    private List<UUID> skills;

    @NotNull
    private UUID ownerId;
}
