package com.demo.backend.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.demo.backend.entity.Social;
import com.demo.backend.entity.User;
import com.demo.backend.repository.SocialRepository;

@Service
public class SocialService {
    private final SocialRepository repository;

    public SocialService(SocialRepository repository) {
        this.repository = repository;
    }

    public Optional<Social> findByUser(User user) {
        return repository.findByUser(user);
    }

    public Social create(User user, String facebook, String line, String instagram) {
        Social entity = new Social();
        entity.setUser(user);
        entity.setFacebook(facebook);
        entity.setLine(line);
        entity.setInstagram(instagram);

        return repository.save(entity);
    }

}
