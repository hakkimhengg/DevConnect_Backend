package com.kshrd.devconnect_springboot.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {
       private String submissionId;
       private Integer score;
       private Long submitTime;
       private UUID challengeId;
       private UUID developerId;
       private LocalDateTime submittedAt;
}
