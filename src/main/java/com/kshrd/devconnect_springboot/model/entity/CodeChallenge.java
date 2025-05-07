package com.kshrd.devconnect_springboot.model.entity;
    
    

import java.util.Date;
import java.util.List;

import com.kshrd.devconnect_springboot.model.templeJsonb.TestCase;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeChallenge {
       private String challengeId;
       private String title;
       private String description;
       private List<TestCase> testCase;
       private String problemDetail;
       private Date createdAt;
       private Integer score;
       private String creatorId;
       private String starterCode;
       private String language;
}
