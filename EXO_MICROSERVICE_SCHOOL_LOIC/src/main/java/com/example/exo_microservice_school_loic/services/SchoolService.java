package com.example.exo_microservice_school_loic.services;

import com.example.exo_microservice_school_loic.dto.SchoolDTO;
import com.example.exo_microservice_school_loic.entities.School;
import com.example.exo_microservice_school_loic.exceptions.RessourceNotFoundException;
import com.example.exo_microservice_school_loic.mappers.SchoolMapper;
import com.example.exo_microservice_school_loic.repositories.SchoolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final SchoolMapper schoolMapper;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public School getSchoolById(Long id) {
        return schoolRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("School not found with id: " + id));
    }

    public Optional<School> getSchoolByName(String name) throws RessourceNotFoundException {
        Optional<School> optionalSchool = schoolRepository.findSchoolByName(name);
        if (optionalSchool.isPresent()) {
            return optionalSchool;
        }
        throw new RessourceNotFoundException("School not found with name: " + name);
    }

    public SchoolDTO updateSchool(Long id, SchoolDTO schoolDTO) {
        School existingSchool = schoolRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("School not found with id: " + id));
        existingSchool.setName(schoolDTO.getName());
        existingSchool.setAddress(schoolDTO.getAddress());
        existingSchool.setDirectorName(schoolDTO.getDirectorName());

        School updatedUser = schoolRepository.save(existingSchool);
        return schoolMapper.toDto(updatedUser);
    }

    public void deleteSchool(Long id) {
        School school = schoolRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("School not found with id: " + id));
        schoolRepository.delete(school);
    }


    public SchoolDTO createSchool(SchoolDTO schoolDTO) throws Exception {
        School school = schoolMapper.toEntity(schoolDTO);
        School savedSchool = schoolRepository.save(school);
        return schoolMapper.toDto(savedSchool);
    }



}
