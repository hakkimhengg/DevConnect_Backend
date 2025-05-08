package com.kshrd.devconnect_springboot.service.implementation;


import com.kshrd.devconnect_springboot.model.dto.request.TopicRequest;
import com.kshrd.devconnect_springboot.model.entity.Topic;
import com.kshrd.devconnect_springboot.respository.TopicRepository;
import com.kshrd.devconnect_springboot.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TopicServiceImplementation implements TopicService {
    private final TopicRepository repository;
    UUID currentUserId = UUID.fromString("e9582541-12d7-4f2f-b921-af1ea9c09795");
    public TopicServiceImplementation(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public Topic getTopicsById(UUID id) {
        return repository.selectTopicsById(id);
    }

    @Override
    public List<Topic> getAllTopics() {
        return repository.getAllTopics();
    }

    @Override
    public Topic createTopics(TopicRequest entity) {
        return repository.insertTopics(entity , currentUserId);
    }

    @Override
    public Topic updateTopics(UUID id, TopicRequest entity) {
        return repository.updateTopics(id, entity , currentUserId);
    }
    @Override
    public Topic deleteTopics(UUID id) {
        return repository.deleteTopics(id);
    }
}
