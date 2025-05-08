package com.kshrd.devconnect_springboot.respository;

import com.kshrd.devconnect_springboot.model.entity.Developer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.UUID;

@Mapper
public interface JoinedHackathonRepository {
    //Get all developers where hackathon id
    //save developers and hackathons when
    @Select("""
            SELECT * FROM join_hackathon+
            INNER JOIN developers d ON jh.developer_id = d.developer_id
            WHERE hackathon_id = #{hackathonId}
            """)
    List<Developer> getAllDevelopersByHackathonId(UUID hackathonId);
}
