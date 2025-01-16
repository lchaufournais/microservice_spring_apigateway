package com.example.exo_microservice_student_loic.services;

import com.example.exo_microservice_student_loic.dto.StudentDTO;
import com.example.exo_microservice_student_loic.entities.Student;
import com.example.exo_microservice_student_loic.exceptions.RessourceNotFoundException;
import com.example.exo_microservice_student_loic.mappers.StudentMapper;
import com.example.exo_microservice_student_loic.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final RestTemplate restTemplate;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper, RestTemplate restTemplate) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.restTemplate = restTemplate;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public ResponseEntity<Map<String, Object>> getStudentById(String id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Student not found with id: " + id));

        JsonNode schoolJson = null;
        try {
            ResponseEntity<JsonNode> schoolResponse = restTemplate.getForEntity(
                    "http://school-service/api/schools/" + student.getSchool_id(), JsonNode.class);
            System.out.println(schoolResponse);
            schoolJson = schoolResponse.getBody();
        } catch (Exception e) {
            throw new RessourceNotFoundException("School service is unavailable or school not found for ID: " + student.getSchool_id());
        }

        StudentDTO studentDTO = studentMapper.toDto(student);

        Map<String, Object> response = new HashMap<>();
        response.put("student", studentDTO);
        response.put("school", schoolJson);

        return ResponseEntity.ok(response);
    }

    public Student getStudentByName(String name) throws RessourceNotFoundException {
        return studentRepository.findStudentByName(name)
                .orElseThrow(() -> new RessourceNotFoundException("Student not found with name: " + name));
    }


    public StudentDTO updateStudent(String id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Student not found with id: " + id));
        existingStudent.setName(studentDTO.getName());
        existingStudent.setGender(studentDTO.getGender());
        existingStudent.setSchool_id(studentDTO.getSchool_id());

        Student updatedStudent = studentRepository.save(existingStudent);
        return studentMapper.toDto(updatedStudent);
    }

    public void deleteStudent(String id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Student not found with id: " + id));
        studentRepository.delete(student);
    }

    public StudentDTO createStudent(StudentDTO studentDTO) throws Exception {
        Student student = studentMapper.toEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }
}
