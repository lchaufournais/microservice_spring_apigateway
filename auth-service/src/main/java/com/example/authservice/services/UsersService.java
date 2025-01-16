package com.example.authservice.services;

import org.springframework.stereotype.Service;
import com.example.authservice.entities.UsersEntity;
import com.example.authservice.repositories.UserRepository;

@Service
public class UsersService {

    private final UserRepository usersRepository;

    public UsersService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersEntity getTokenById(String token) {
        return usersRepository.findByToken(token);
    }

    public void addAuthtoken(UsersEntity authtoken) {
        usersRepository.save(authtoken);
    }
}