package com.kshrd.devconnect_springboot.respository;

import com.kshrd.devconnect_springboot.config.UuidTypeHandler;
import com.kshrd.devconnect_springboot.model.entity.Position;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.UUID;

@Mapper
public interface ProjectPositionRepository {
    @Result(property = "positionId", jdbcType = JdbcType.OTHER, javaType = UUID.class,typeHandler = UuidTypeHandler.class, column = "position_id")
    @Result(property = "positionName", column = "position_name")
    @Select("""
        SELECT * FROM project_positions pp INNER JOIN positions p ON p.position_id = pp.position_id WHERE project_id = #{projectId}::uuid
    """)
    List<Position> getAllProjectPositionById(UUID projectId);

    @Insert("""
        INSERT INTO project_positions VALUES (#{projectId}, #{positionId})
    """)
    void createProjectPosition(UUID projectId, UUID positionId);

    @Delete("""
        DELETE FROM project_positions WHERE project_id = #{projectId}::uuid
    """)
    void deleteAllProjectPosition(UUID projectId);
}
