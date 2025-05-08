package com.kshrd.devconnect_springboot.service.implementation;

import com.kshrd.devconnect_springboot.model.dto.request.ResumeRequest;
import com.kshrd.devconnect_springboot.model.entity.Resume;
import com.kshrd.devconnect_springboot.respository.ResumeRepository;
import com.kshrd.devconnect_springboot.service.ResumeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResumeServiceImpl implements ResumeService {
    private final ResumeRepository repository;
    UUID appUserId = UUID.fromString("a0b1c2d3-e4f5-6789-abcd-ef0123456789");
    public ResumeServiceImpl(ResumeRepository repository) {
        this.repository = repository;
    }
    @Override
    public Resume getResumesById() {
        return repository.selectResumesById(appUserId);
    }
    @Override
    public Resume createResumes(ResumeRequest entity) {
        return repository.insertResumes(entity , appUserId);
    }

    @Override
    public Resume updateResumes(UUID id, ResumeRequest entity) {

        return repository.updateResumes(id, entity , appUserId);
    }

    @Override
    public Resume deleteResumes(UUID id) {
        return repository.deleteResumes(id);
    }
}
