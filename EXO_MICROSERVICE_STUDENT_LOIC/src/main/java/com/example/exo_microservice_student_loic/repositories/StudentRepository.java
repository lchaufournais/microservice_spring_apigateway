package com.example.exo_microservice_student_loic.repositories;


import com.example.exo_microservice_student_loic.entities.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {
    Optional<Student> findStudentByName(String name);
}