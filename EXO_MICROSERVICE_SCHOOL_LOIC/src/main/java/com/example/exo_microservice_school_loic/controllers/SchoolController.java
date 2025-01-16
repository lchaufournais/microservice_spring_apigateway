package com.example.exo_microservice_school_loic.controllers;

import com.example.exo_microservice_school_loic.dto.SchoolDTO;
import com.example.exo_microservice_school_loic.entities.School;
import com.example.exo_microservice_school_loic.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {
    @Autowired
    SchoolService schoolService;

    @GetMapping
    public ResponseEntity<List<School>> getAllSchools() {
        return ResponseEntity.ok(schoolService.getAllSchools());
    }

    @PostMapping
    public ResponseEntity<SchoolDTO> createSchool(@RequestBody SchoolDTO schoolDTO) {
        try {
            SchoolDTO savedSchool = schoolService.createSchool(schoolDTO);
            return ResponseEntity.ok(savedSchool);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolDTO> updateSchool(@PathVariable Long id, @RequestBody SchoolDTO schoolDTO) {
        try {
            SchoolDTO savedSchool = schoolService.updateSchool(id, schoolDTO);
            return ResponseEntity.ok(savedSchool);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Long id) {
        try {
            School schoolDTO = schoolService.getSchoolById(id);
            return ResponseEntity.ok(schoolDTO);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

}