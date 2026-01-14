package com.example.studentmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanager.entities.Student;
import com.example.studentmanager.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    // List all students
    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    // Read a student by ID
    public Optional<Student> getStudentById(Integer id) {
        return repository.findById(id);
    }

    // Create
    public Student addStudent(Student student) {
        return repository.save(student);
    }

    // Update
    public Student updateStudent(Student student) {
        return repository.save(student);
    }

    // Delete
    public void deleteStudent(Integer id) {
        repository.deleteById(id);
    }

    // Search by name// Find student by exact name
    public Optional<Student> getStudentByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }
}
