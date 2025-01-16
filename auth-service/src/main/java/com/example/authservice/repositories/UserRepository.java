package com.example.authservice.repositories;

import com.example.authservice.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    UsersEntity findByToken(String token);

}