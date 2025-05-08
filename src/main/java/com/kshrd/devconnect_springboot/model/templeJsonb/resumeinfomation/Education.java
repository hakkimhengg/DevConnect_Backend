package com.kshrd.devconnect_springboot.model.templeJsonb.resumeinfomation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private String schoolName;
    private String schoolLocation;
    private Integer startYear;
    private Integer endYear;
    private String description;
}