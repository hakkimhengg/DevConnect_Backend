package com.kshrd.devconnect_springboot.respository;

import com.kshrd.devconnect_springboot.config.UuidTypeHandler;
import com.kshrd.devconnect_springboot.model.entity.Skill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.UUID;

@Mapper
public interface SkillRepository {
    @Result(property = "skillId", jdbcType = JdbcType.OTHER, javaType = UUID.class,typeHandler = UuidTypeHandler.class, column = "skill_id")
    @Result(property = "skillName", column = "skill_name")
    @Select("""
        SELECT * FROM skills
    """)
    List<Skill> getAllSkill();

    @Result(property = "skillId", jdbcType = JdbcType.OTHER, javaType = UUID.class,typeHandler = UuidTypeHandler.class, column = "skill_id")
    @Result(property = "skillName", column = "skill_name")
    @Select("""
        SELECT * FROM skills WHERE skill_id = #{skillId}::uuid
    """)
    Skill getSkillById(UUID skillId);
}
