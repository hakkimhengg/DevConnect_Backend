package com.kshrd.devconnect_springboot.service;

import com.kshrd.devconnect_springboot.model.dto.request.ResumeRequest;
import com.kshrd.devconnect_springboot.model.entity.Resume;

import java.util.List;
import java.util.UUID;

public interface ResumeService {
    Resume getResumesById();
    Resume createResumes(ResumeRequest entity);
    Resume updateResumes (UUID id, ResumeRequest entity);
    Resume deleteResumes(UUID id);
}
