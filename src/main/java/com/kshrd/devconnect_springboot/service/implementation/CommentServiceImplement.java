package com.kshrd.devconnect_springboot.service.implementation;


import com.kshrd.devconnect_springboot.model.dto.request.CommentRequest;
import com.kshrd.devconnect_springboot.model.entity.Comment;
import com.kshrd.devconnect_springboot.respository.CommentRepository;
import com.kshrd.devconnect_springboot.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImplement implements CommentService {
    private final CommentRepository repository;
    UUID currentUserId = UUID.fromString("e9582541-12d7-4f2f-b921-af1ea9c09795");
    public CommentServiceImplement(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Comment getCommentsById(UUID id) {
        return repository.selectCommentsByTopicId(id);
    }

    @Override
    public List<Comment> getAllComments() {
        return repository.getAllComments();
    }

    @Override
    public Comment createComments(CommentRequest entity , UUID topicId) {
        return repository.insertComments(entity , currentUserId , topicId);
    }

    @Override
    public Comment updateComments(UUID id, CommentRequest entity) {
        return repository.updateComments(entity,id, currentUserId);
    }

    @Override
    public Comment deleteComments(UUID id) {
        return repository.deleteComments(id);
    }

    @Override
    public Comment insertReplyComment(CommentRequest entity , UUID commentId ) {
        return repository.insertReplyComment(entity , currentUserId , commentId );
    }

    @Override
    public Comment updateReplyComment(UUID id, CommentRequest entity) {
        return null;
    }
}
