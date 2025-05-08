package com.kshrd.devconnect_springboot.service;

import com.kshrd.devconnect_springboot.model.dto.request.ProjectRequest;
import com.kshrd.devconnect_springboot.model.entity.Project;

import java.util.List;
import java.util.UUID;

public interface ProjectService {
    List<Project> getAllProject(Integer page, Integer size);
    Project getProjectById(UUID projectId);
    List<Project> getAllProjectByUser(Integer page, Integer size);
    Project getProjectByIdAndUser(UUID projectId);
    Project createProject(ProjectRequest projectRequest);
    Project updateProject(UUID projectId, ProjectRequest projectRequest);
}
