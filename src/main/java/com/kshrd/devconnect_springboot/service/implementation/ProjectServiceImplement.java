package com.kshrd.devconnect_springboot.service.implementation;

import com.kshrd.devconnect_springboot.exception.BadRequestException;
import com.kshrd.devconnect_springboot.exception.NotFoundException;
import com.kshrd.devconnect_springboot.model.dto.request.ProjectRequest;
import com.kshrd.devconnect_springboot.model.entity.AppUser;
import com.kshrd.devconnect_springboot.model.entity.Project;
import com.kshrd.devconnect_springboot.respository.ProjectPositionRepository;
import com.kshrd.devconnect_springboot.respository.ProjectRepository;
import com.kshrd.devconnect_springboot.respository.ProjectSkillRepository;
import com.kshrd.devconnect_springboot.service.ProjectService;
import com.kshrd.devconnect_springboot.utils.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImplement implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectPositionRepository projectPositionRepository;
    private final ProjectSkillRepository projectSkillRepository;
    private final AppUser currentUser = CurrentUser.appUser;

    @Override
    public List<Project> getAllProject(Integer page, Integer size) {
        page = (page - 1) * size;
        return projectRepository.getAllProject(page, size);
    }

    @Override
    public Project getProjectById(UUID projectId) {
        Project project = projectRepository.getProjectById(projectId);
        if(project == null) {
            throw new NotFoundException("Project not found");
        }
        return project;
    }

    @Override
    public List<Project> getAllProjectByUser(Integer page, Integer size) {
        page = (page - 1) * size;
        return projectRepository.getAllProjectByUser(currentUser.getUserId(), page, size);
    }

    @Override
    public Project getProjectByIdAndUser(UUID projectId) {
        Project project = projectRepository.getProjectByIdAndUser(currentUser.getUserId(), projectId);
        if(project == null) {
            throw new NotFoundException("Project not found");
        }
        return project;
    }

    @Override
    public Project createProject(ProjectRequest projectRequest) {
        Project project = projectRepository.createProjectByUser(currentUser.getUserId(), projectRequest);
        for (UUID p : projectRequest.getSkills()) {
            projectSkillRepository.createProjectSkill(project.getProjectId(), p);
        }
        for (UUID p : projectRequest.getPositions()) {
            projectPositionRepository.createProjectPosition(project.getProjectId(), p);
        }
        return getProjectById(project.getProjectId());
    }

    @Override
    public Project updateProject(UUID projectId, ProjectRequest projectRequest) {
        if(getProjectById(projectId) == null) {
            throw new NotFoundException("Project not found");
        }
        Project project = projectRepository.updateProject(projectId, projectRequest);
        projectSkillRepository.deleteAllProjectSkill(projectId);
        projectPositionRepository.deleteAllProjectPosition(projectId);
        for (UUID p : projectRequest.getSkills()) {
            projectSkillRepository.createProjectSkill(project.getProjectId(), p);
        }
        for (UUID p : projectRequest.getPositions()) {
            projectPositionRepository.createProjectPosition(project.getProjectId(), p);
        }
        return getProjectById(project.getProjectId());
    }
}
