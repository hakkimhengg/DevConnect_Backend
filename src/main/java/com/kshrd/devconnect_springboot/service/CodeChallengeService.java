package com.kshrd.devconnect_springboot.service;


import com.kshrd.devconnect_springboot.model.dto.request.CodeChallengeRequest;
import com.kshrd.devconnect_springboot.model.entity.CodeChallenge;

import java.util.List;
import java.util.UUID;

public interface CodeChallengeService {
    CodeChallenge getCodeChallengeById(UUID id);
    List<CodeChallenge> getAllCodeChallenge();
    CodeChallenge createCodeChallenge(CodeChallengeRequest entity);
    CodeChallenge updateCodeChallenge (UUID id, CodeChallengeRequest entity);
    CodeChallenge deleteCodeChallenge(UUID id);
}
