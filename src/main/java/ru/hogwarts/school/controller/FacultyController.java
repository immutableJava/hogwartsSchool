package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    private final Logger logger = LoggerFactory.getLogger(FacultyController.class);

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty currentFaculty = facultyService.updateFaculty(faculty);
        if (faculty == null) {
            logger.error("Faculty not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(currentFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        if (facultyService.getFacultyById(id) == null && facultyService.findAllFaculties().size() != 0) {
            logger.error("There is no faculty with id = " + id);
            return ResponseEntity.notFound().build();
        }
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findByColor")
    public ResponseEntity<Collection<Faculty>> findByColor(@RequestParam String color) {
        return ResponseEntity.ok(facultyService.findByColor(color));
    }

    @GetMapping()
    public ResponseEntity<Collection<Faculty>> findAllFaculties() {
        return ResponseEntity.ok(facultyService.findAllFaculties());
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<Faculty> findFacultyByName(@PathVariable String name) {
        return ResponseEntity.ok(facultyService.findFacultyByName(name));
    }

    @GetMapping("/findTheLongestFacultyName")
    public ResponseEntity<String> findTheLongestFacultyName() {
        return ResponseEntity.ok(facultyService.findTheLongestFacultyName());
    }

}
