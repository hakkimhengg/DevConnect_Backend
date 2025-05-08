package com.kshrd.devconnect_springboot.model.entity;

import com.kshrd.devconnect_springboot.model.templeJsonb.resumeinfomation.ResumeInformation;
import lombok.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {
       private UUID resumeId;
       private String fullName;
       private String phoneNumber;
       private String address;
       private String email;
       private Date dob;
       private String position;
       private String description;
       private ResumeInformation information;
       private UUID developerId;
}
