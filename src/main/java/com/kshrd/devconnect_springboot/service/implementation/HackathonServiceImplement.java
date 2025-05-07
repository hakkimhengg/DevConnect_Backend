package com.kshrd.devconnect_springboot.service.implementation;

import com.kshrd.devconnect_springboot.model.dto.request.HackathonRequest;
import com.kshrd.devconnect_springboot.model.entity.AppUser;
import com.kshrd.devconnect_springboot.model.entity.Hackathon;
import com.kshrd.devconnect_springboot.respository.HackathonRepository;
import com.kshrd.devconnect_springboot.service.HackathonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public  class HackathonServiceImplement implements HackathonService {
    private final HackathonRepository hackathonRepository;

    @Override
    public List<Hackathon> getAllHackathons(Long page, Long size) {
        return hackathonRepository.getAllHackathons(page, size);
    }

    @Override
    public Hackathon getHackathonById(UUID hackathonId) {
        return hackathonRepository.getHackathonById(hackathonId);
    }

    @Override
    public Hackathon updateHackathonById(UUID hackathonId, HackathonRequest request) {
        return hackathonRepository.updateHackathonById(hackathonId, request);
    }

    @Override
    public Hackathon createHackathon(HackathonRequest request) {
        return hackathonRepository.createHackathon(request);
    }

    @Override
    public void deleteHackathonById(UUID hackathonId) {
        hackathonRepository.deleteHackathonById(hackathonId);
    }
}
