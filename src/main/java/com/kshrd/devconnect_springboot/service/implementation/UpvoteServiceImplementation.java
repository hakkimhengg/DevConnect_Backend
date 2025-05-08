package com.kshrd.devconnect_springboot.service.implementation;


import com.kshrd.devconnect_springboot.model.entity.Upvote;
import com.kshrd.devconnect_springboot.respository.CommentRepository;
import com.kshrd.devconnect_springboot.respository.UpvoteRepository;
import com.kshrd.devconnect_springboot.service.UpvoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpvoteServiceImplementation implements UpvoteService {

    private final UpvoteRepository repository;
    private final CommentRepository commentsRepository;
    UUID currentUserId = UUID.fromString("e9582541-12d7-4f2f-b921-af1ea9c09795");
    @Override
    public Upvote createUpvote(UUID commentId) {
        Upvote upvote = Upvote.builder()
                .commentId(commentId)
                .userId(currentUserId)
                .build();
        Upvote inserted = repository.insertUpvote(upvote);

        Integer total = repository.countUpvote(commentId);
        commentsRepository.addUpvote(commentId , total);
        return inserted;
    }
    @Override
    public Upvote deleteUpvote(UUID commentId) {
        Upvote upvote = Upvote.builder()
                .commentId(commentId)
                .userId(currentUserId)
                .build();
        Upvote deleteUpvote = repository.deleteUpvote(upvote);
        Integer total = repository.countUpvote(commentId);
        commentsRepository.addUpvote( commentId , total);
        return deleteUpvote;
    }
}
