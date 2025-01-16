package com.example.exo_microservice_student_loic.mappers;

import com.example.exo_microservice_student_loic.dto.StudentDTO;
import com.example.exo_microservice_student_loic.entities.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDto(Student student);
    Student toEntity(StudentDTO studentDTO);
}
