package com.kshrd.devconnect_springboot.service;

import com.kshrd.devconnect_springboot.model.dto.request.TopicRequest;
import com.kshrd.devconnect_springboot.model.entity.Topic;

import java.util.List;
import java.util.UUID;

public interface TopicService {
    Topic getTopicsById(UUID id);
    List<Topic> getAllTopics();
    Topic createTopics(TopicRequest entity);
    Topic updateTopics (UUID id, TopicRequest entity);
    Topic deleteTopics(UUID id);
}
