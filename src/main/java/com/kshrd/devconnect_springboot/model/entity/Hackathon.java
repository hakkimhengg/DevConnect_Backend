package com.kshrd.devconnect_springboot.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hackathon {
    private UUID hackathonId;
    private String hackathonTitle;
    private String hackathonDescription;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdDate = LocalDateTime.now();
    private Boolean isAvailable;
    private UUID creatorId; //Recruiter Id
}
