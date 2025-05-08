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
    UUID developerId = UUID.fromString("e9582541-12d7-4f2f-b921-af1ea9c09795"); // edit here when have auth
    public ResumeServiceImpl(ResumeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Resume getResumesById(UUID id) {
        return repository.selectResumesById(id);
    }

    @Override
    public List<Resume> getAllResumes() {
        return repository.selectResumesByDeveloperId(developerId);
    }

    @Override
    public Resume createResumes(ResumeRequest entity) {
        return repository.insertResumes(entity , developerId);
    }

    @Override
    public Resume updateResumes(UUID id, ResumeRequest entity) {

        return repository.updateResumes(id, entity , developerId);
    }

    @Override
    public Resume deleteResumes(UUID id) {
        return repository.deleteResumes(id);
    }
}
