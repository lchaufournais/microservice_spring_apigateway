package com.example.exo_microservice_school_loic.repositories;

import com.example.exo_microservice_school_loic.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {
    Optional<School> findSchoolByName(String name);
}