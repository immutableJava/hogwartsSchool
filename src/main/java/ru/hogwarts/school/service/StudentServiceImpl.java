package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.NullStudentException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Method createStudent is called and worked correct");
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        logger.warn("If id == null, it can throw NullStudentException");
        Student student = studentRepository.findById(id).orElseThrow(NullStudentException::new);
        logger.warn("If repository has not any student with current id he can return error 404");
        logger.info("Method getStudentById is called and worked correct");
        return student;
    }

    public void writeFirstSixNames() {
        List<String> nameList = studentRepository
                .findAll()
                .stream()
                .map(Student::getName)
                .toList();
        logger.info("Method getNames is called and worked correct");

        System.out.println(nameList.get(1));
        System.out.println(nameList.get(2));

        Thread firstThread = new Thread(() -> {
            System.out.println(nameList.get(2));
            System.out.println(nameList.get(3));
        });
        firstThread.start();

        Thread secondThread = new Thread(() -> {
            System.out.println(nameList.get(4));
            System.out.println(nameList.get(5));
        });
        secondThread.start();
    }

    public Student updateStudent(Student student) {
        logger.info("Method updateStudent is called and worked correct");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("Method deleteStudent is called and worked correct");
        studentRepository.deleteById(id);
    }

    public Collection<Student> findAllByAge(int age) {
        logger.info("Method findByAge is called and worked correct");
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> findAllStudents() {
        logger.info("Method getAllStudents is called and worked correct");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Method findByAgeBetween is called and worked correct");
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Collection<Student> findByFirstLetter(String letter) {
        return studentRepository.findAll()
                .stream()
                .filter(s -> s.getName().startsWith(letter))
                .sorted()
                .toList();
    }

    public Integer getAmountOfStudents() {
        logger.info("Method getAmountOfStudents is called and worked correct");
        return studentRepository.getAmountOfStudents();
    }

    public Double getAverageAgeOfStudents() {
        logger.info("Method getAverageAgeOfStudents is called and worked correct");
        return studentRepository.getAverageAgeOfStudents();
    }

    @Override
    public List<Student> getLastFiveStudents() {
        logger.info("Method getLastFiveStudents is called and worked correct");
        return studentRepository.getLastFiveStudents();
    }


}
