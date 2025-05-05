package com.kshrd.devconnect_springboot.respository;

import com.kshrd.devconnect_springboot.config.UuidTypeHandler;
import com.kshrd.devconnect_springboot.model.dto.request.AppUserRequest;
import com.kshrd.devconnect_springboot.model.entity.AppUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;

@Mapper
public interface AppUserRepository {
    @Results(id = "authMapper", value = {
            @Result(property = "userId", jdbcType = JdbcType.OTHER, javaType = UUID.class,typeHandler = UuidTypeHandler.class, column = "user_id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "profileImageUrl", column = "profile_image_url"),
            @Result(property = "isRecruiter", column = "is_recruiter"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    })
    @Select("""
        SELECT * FROM app_users WHERE email = #{email}
    """)
    AppUser getUserByEmail(String email);

    @Update("""
         UPDATE app_users SET is_verified = true WHERE email = #{email}
    """)
    void updateVerificationStatus(String email);
}
