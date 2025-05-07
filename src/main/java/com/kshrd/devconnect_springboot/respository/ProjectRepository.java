package com.kshrd.devconnect_springboot.respository;

import com.kshrd.devconnect_springboot.config.UuidTypeHandler;
import com.kshrd.devconnect_springboot.model.dto.request.ProjectRequest;
import com.kshrd.devconnect_springboot.model.entity.Project;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ProjectRepository {
    @Results(id = "projectMapper", value = {
            @Result(property = "projectId", jdbcType = JdbcType.OTHER, javaType = UUID.class,typeHandler = UuidTypeHandler.class, column = "project_id"),
            @Result(property = "isOpen", column = "is_open"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "ownerId", column = "owner_id", one = @One(select = "com.kshrd.devconnect_springboot.respository.AppUserRepository.getUserById")),
            @Result(property = "skills", jdbcType = JdbcType.OTHER, javaType = UUID.class,typeHandler = UuidTypeHandler.class, column = "project_id", many = @Many(select = "com.kshrd.devconnect_springboot.respository.ProjectSkillRepository.getSkillByProjectId")),
            @Result(property = "positions", jdbcType = JdbcType.OTHER, javaType = UUID.class,typeHandler = UuidTypeHandler.class, column = "project_id", many = @Many(select = "com.kshrd.devconnect_springboot.respository.ProjectPositionRepository.getAllProjectPositionById"))
    })
    @Select("""
        SELECT * FROM projects
        OFFSET #{page} LIMIT #{size};
    """)
    List<Project> getAllProject(Integer page, Integer size);

    @ResultMap("projectMapper")
    @Select("""
        SELECT * FROM projects WHERE project_id = #{projectId}::uuid
    """)
    Project getProjectById(UUID projectId);

    @ResultMap("projectMapper")
    @Select("""
        SELECT * FROM projects WHERE owner_id = #{ownerId}::uuid
        OFFSET #{page} LIMIT #{size};
    """)
    List<Project> getAllProjectByUser(UUID ownerId, Integer page, Integer size);

    @ResultMap("projectMapper")
    @Select("""
        SELECT * FROM projects WHERE owner_id = #{ownerId}::uuid AND project_id = #{projectId}::uuid
    """)
    Project getProjectByIdAndUser(UUID ownerId, UUID projectId);

    @ResultMap("projectMapper")
    @Select("""
        INSERT INTO projects VALUES (DEFAULT, #{req.title}, #{req.description}, #{req.isOpen}, DEFAULT, #{ownerId})
        RETURNING *;
    """)
    Project createProjectByUser(UUID ownerId, @Param("req") ProjectRequest request);

    @ResultMap("projectMapper")
    @Select("""
        UPDATE projects SET title = #{req.title}, description = #{req.description}, is_open = #{req.isOpen} WHERE project_id = #{projectId}
        RETURNING *;
    """)
    Project updateProject(UUID projectId, @Param("req") ProjectRequest request);

    @Delete("""
        DELETE FROM projects WHERE project_id = #{projectId}::uuid
    """)
    void deleteProject(UUID projectId);
}