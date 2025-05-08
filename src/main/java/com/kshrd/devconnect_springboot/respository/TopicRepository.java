package com.kshrd.devconnect_springboot.respository;

import com.kshrd.devconnect_springboot.model.dto.request.TopicRequest;
import com.kshrd.devconnect_springboot.model.entity.Topic;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

@Mapper
public interface TopicRepository {
 
    // GET Topic BY ID
    @Select("""
        SELECT 
            t.topic_id,
            t.content AS topic_content,
            t.created_at AS topic_posted_at,
            t.creator_id AS topic_creator_id,
            c.comment_id,
            c.text AS comment_content,
            c.created_at AS comment_posted_at,
            c.total_upvotes,
            c.user_id AS comment_creator_id
        FROM topics t
        LEFT JOIN comments c ON t.topic_id = c.topic_id
        WHERE t.topic_id = #{topicId}
""")
    @Results(id = "BaseResultMap", value = {
            @Result(property = "topicId", column = "topic_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "postedAt", column = "posted_at"),
            @Result(property = "creator", column = "creator_id" ,
                    one = @One(select = "com.kshrd.devconnect_springboot.respository.AppUserRepository.getUserById")),
            @Result(property = "comments", column = "topic_id",
                    many = @Many(select = "com.kshrd.devconnect_springboot.respository.CommentRepository.selectCommentsByTopicId"))
    })
    Topic selectTopicsById(@Param("id") UUID id);
    
    // DELETE Topic
    @Select("""
        DELETE
        FROM topics
        WHERE topic_id = #{topicId}
        RETURNING *
        """)
        @ResultMap("BaseResultMap")
    Topic deleteTopics(UUID topicId);

    // INSERT Topic
    @Select("""
        INSERT INTO topics
        (content, created_at, creator_id)
        VALUES
        (
            #{topics.content},
            #{topics.postedAt},
            #{creatorId}
        )
        RETURNING *;
        """)
        @ResultMap("BaseResultMap")
   
    Topic insertTopics(@Param("topics") TopicRequest entity , UUID creatorId);

    // UPDATE  Topic
    @Select("""
    UPDATE topics
    SET
         content = #{topics.content},
         created_at = #{topics.postedAt},
         creator_id = #{creatorId}
    WHERE topic_id = #{id}
    RETURNING *;
    """)
    @ResultMap("BaseResultMap")
    
    Topic updateTopics(UUID id , @Param("topics") TopicRequest entity , UUID creatorId);
    
    // GET ALL Topic
        
    @Select("""
        SELECT * FROM topics
    """)
    @ResultMap("BaseResultMap")
    
    List<Topic> getAllTopics();
}
