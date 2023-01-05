package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.NullFacultyException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Comparator;

@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {

    private final Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
        logger.info("Method createFaculty is called and worked correct.");
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long id) {
        logger.warn("If id == null, it can throw NullFacultyException");
        logger.warn("If repository has not any faculty with current id he can return error 404");
        logger.info("Method getFacultyById is called and worked correct.");
        return facultyRepository.findById(id).orElseThrow(NullFacultyException::new);
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Method updateFaculty is called and worked correct.");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        logger.info("Method deleteFaculty is called and worked correct.");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> findByColor(String color) {
        logger.info("Method filterByColor is called and worked correct.");
        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> findAllFaculties() {
        logger.info("Method getAllFaculties is called and worked correct.");
        return facultyRepository.findAll();
    }

    @Override
    public Faculty findFacultyByName(String name) {
        logger.info("Method findFacultyByName is called and worked correct.");
        return facultyRepository.findByNameIgnoreCase(name);
    }

    @Override
    public String findTheLongestFacultyName() {
        return facultyRepository.findAll()
                .stream()
                .max(Comparator.comparingInt(f -> f.getName().length()))
                .orElseThrow()
                .getName();
    }
}
