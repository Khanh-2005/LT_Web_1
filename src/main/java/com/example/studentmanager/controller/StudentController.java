package com.example.studentmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.studentmanager.entities.Student;
import com.example.studentmanager.service.StudentService;

import org.springframework.http.ResponseEntity;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // LIST ALL STUDENTS (VIEW)
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    // API: GET ALL STUDENTS AS JSON
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Student>> getAllStudentsApi() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // VIEW STUDENT DETAILS
    @GetMapping("/{id}")
    public String viewStudent(@PathVariable Integer id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        model.addAttribute("student", student);
        return "student_details";
    }

    // SHOW CREATE FORM
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "create_student";
    }

    // CREATE STUDENT (POST /students)
    @PostMapping
    public String addStudent(
            @Validated @ModelAttribute("student") Student student,
            BindingResult br,
            RedirectAttributes ra) {

        if (br.hasErrors()) {
            return "create_student";
        }

        studentService.addStudent(student);
        ra.addFlashAttribute("success", "Student created");
        return "redirect:/students";
    }

    // SHOW UPDATE FORM
    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));
        model.addAttribute("student", student);
        return "update_student";
    }

    // UPDATE STUDENT
    @PostMapping("/{id}")
    public String updateStudent(
            @PathVariable Integer id,
            @Validated @ModelAttribute("student") Student student,
            BindingResult br,
            RedirectAttributes ra) {

        if (br.hasErrors()) {
            return "update_student";
        }

        Student existing = studentService.getStudentById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student ID: " + id));

        existing.setName(student.getName());
        existing.setAge(student.getAge());
        existing.setEmail(student.getEmail());

        studentService.updateStudent(existing);
        ra.addFlashAttribute("success", "Student updated");
        return "redirect:/students";
    }

    // DELETE STUDENT
    @PostMapping("/{id}/delete")
    public String deleteStudent(@PathVariable Integer id, RedirectAttributes ra) {
        studentService.deleteStudent(id);
        ra.addFlashAttribute("success", "Student deleted");
        return "redirect:/students";
    }

    // SEARCH STUDENTS BY NAME
    @GetMapping("/search-by-name")
    public String searchStudentByName(
            @RequestParam("name") String name,
            Model model) {

        Student student = studentService.getStudentByName(name)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + name));

        model.addAttribute("student", student);
        return "student_details"; // view chi tiết 1 sinh viên
    }

}
