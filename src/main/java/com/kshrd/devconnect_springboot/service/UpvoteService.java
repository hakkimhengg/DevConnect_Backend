package com.kshrd.devconnect_springboot.service;

import com.kshrd.devconnect_springboot.model.entity.Upvote;

import java.util.UUID;

public interface UpvoteService {
    Upvote createUpvote(UUID commentId);
    Upvote deleteUpvote(UUID commentId);
}
