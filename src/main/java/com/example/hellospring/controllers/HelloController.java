package com.example.hellospring.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.example.hellospring.Model.Student;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class HelloController {

    // Bai 1
    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot API";
    }

    // Bai 2
    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Hello " + name;
    }

    @GetMapping("/students/search")
    public String search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page) {
        return "keyword= " + keyword + ", page= " + page;
    }

    // Bai 3
    @GetMapping("/students/{id}")
    public String getStudent(@PathVariable int id) {
        return "Student ID: " + id;
    }

    // Bai 4
    @GetMapping("/student")
    public Student getStudent() {
        return new Student(1, "Khanh", 20);
    }

    // Bai 5
    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, "Kien", 20));
        list.add(new Student(2, "Hoang", 20));
        return list;
    }
}
