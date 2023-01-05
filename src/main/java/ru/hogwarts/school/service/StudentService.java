package ru.hogwarts.school.service;

import ru.hogwarts.school.model.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);

    Student getStudentById(Long id);

    Student updateStudent(Student student);

    void deleteStudent(Long id);

    void writeFirstSixNames();

    Collection<Student> findAllByAge(int age);

    Collection<Student> findAllStudents();

    Collection<Student> findByAgeBetween(int min, int max);

    Collection<Student> findByFirstLetter(String letter);

    Integer getAmountOfStudents();

    Double getAverageAgeOfStudents();

    List<Student> getLastFiveStudents();


}
