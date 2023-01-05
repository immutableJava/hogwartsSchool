package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("query-methods")
public class StudentWithQueryMethodsController {
    private final StudentService studentService;

    public StudentWithQueryMethodsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/amount")
    public Integer getAmountOfStudents() {
        return studentService.getAmountOfStudents();
    }

    @GetMapping("/avg-age")
    public Double getAverageAgeOfStudents() {
        return studentService.getAverageAgeOfStudents();
    }

    @GetMapping("/last-five-students")
    public List<Student> getLastFiveOfStudents() {
        return studentService.getLastFiveStudents();
    }
}
