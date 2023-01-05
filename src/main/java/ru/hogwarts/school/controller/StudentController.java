package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;


@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student currentStudent = studentService.createStudent(student);
        return ResponseEntity.ok(currentStudent);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student currentStudent = studentService.updateStudent(student);
        if (student == null) {
            logger.error("Student not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(currentStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("studentId") Long id) {
        if (studentService.getStudentById(id) == null) {
            logger.error("There is no student with id = " + id);
            return ResponseEntity.notFound().build();
        }
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/writeFirstSixNames")
    public ResponseEntity<String> writeAllNames() {
        studentService.writeFirstSixNames();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findByAge")
    public ResponseEntity<Collection<Student>> findAllByAge(@RequestParam int age) {
        return ResponseEntity.ok(studentService.findAllByAge(age));
    }

    @GetMapping()
    public ResponseEntity<Collection<Student>> findAllStudents() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @GetMapping("/findByAgeBetween")
    public ResponseEntity<Collection<Student>> findByAgeBetween(int min, int max) {
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @GetMapping("/findByFirstLetter")
    public ResponseEntity<Collection<Student>> findByFirstLetter(String letter) {
        return ResponseEntity.ok(studentService.findByFirstLetter(letter));
    }


}
