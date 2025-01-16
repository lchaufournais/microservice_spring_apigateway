package com.example.exo_microservice_school_loic.mappers;

import com.example.exo_microservice_school_loic.dto.SchoolDTO;
import com.example.exo_microservice_school_loic.entities.School;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolMapper {
    SchoolDTO toDto(School school);
    School toEntity(SchoolDTO schoolDTO);
}
