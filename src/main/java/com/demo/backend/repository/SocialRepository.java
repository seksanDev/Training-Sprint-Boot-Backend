package com.demo.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.demo.backend.entity.Social;
import com.demo.backend.entity.User;

public interface SocialRepository extends CrudRepository<Social,String>{
    Optional<Social> findByUser(User user);
}

