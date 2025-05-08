package com.kshrd.devconnect_springboot.respository;

import com.kshrd.devconnect_springboot.model.dto.request.HackathonRequest;
import com.kshrd.devconnect_springboot.model.entity.Hackathon;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface HackathonRepository {

    @Results(id = "hackathonMapping", value = {
            @Result(property = "hackathonId", column = "hackathon_id"),
            @Result(property = "hackathonTitle", column = "title"),
            @Result(property = "hackathonDescription", column = "description"),
            @Result(property = "startDate", column = "started_at"),
            @Result(property = "endDate", column = "finished_at"),
            @Result(property = "createdDate", column = "created_at"),
            @Result(property = "isAvailable", column = "is_available"),
            @Result(property = "creatorId", column = "creator_id"),    //Recruiter Id
            @Result(property = "developerId", column = "developer_id")
    })

    @Select("""
            SELECT * FROM hackathons
            OFFSET #{page} LIMIT #{size}
            """)
    List<Hackathon> getAllHackathons(Long page, Long size);

    @ResultMap("hackathonMapping")
    @Select("""
            SELECT * FROM hackathons
            WHERE hackathon_id = #{hackathonId}
            """)
    Hackathon getHackathonById(UUID hackathonId);

    @ResultMap("hackathonMapping")
    @Select("""
            UPDATE hackathons
            SET title = #{req.hackathonTitle},
                description = #{req.hackathonDescription},
                started_at = #{req.startDate},
                finished_at = #{req.endDate},
                created_at = #{req.createdDate},
                is_available = #{req.isAvailable},
                creator_id = #{req.creatorId}
            WHERE hackathon_id = #{hackathonId};
            RETURNING *;
            """)
    Hackathon updateHackathonById(UUID hackathonId, @Param("req") HackathonRequest request);

    @ResultMap("hackathonMapping")
    @Insert("""
            INSERT INTO hackathons (title, description, started_at, finished_at, created_at, is_available, creator_id
            ) VALUES (
                #{req.hackathonTitle},
                #{req.hackathonDescription},
                #{req.startDate},
                #{req.endDate},
                #{req.createdDate},
                #{req.isAvailable},
                #{req.creatorId}
            )
            RETURNING *;
            """)
    Hackathon createHackathon(@Param("req") HackathonRequest request);

    @Delete("""
            DELETE FROM hackathon WHERE hackathon_id = #{hackathonId}
            """)
    void deleteHackathonById(UUID hackathonId);
}

