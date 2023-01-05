package ru.hogwarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationStudentTest {

    @Value("${local.server.port}")
    private int port;


    @Autowired
    private StudentController studentController;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setName("Tamerlan");
        student.setAge(20);
        student.setId(1L);
        System.out.println(port);
        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student/"
                , student, String.class)).isNotNull();
    }

    @Test
    void testGetStudentById() {
        long id = 2L;
        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student/" + id
                , String.class)).isNotNull();
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student();
        student.setName("Heisenberg");
        student.setAge(52);
        student.setId(2L);
        HttpEntity<Student> entity = new HttpEntity<>(student);
        ResponseEntity<Student> response = restTemplate.exchange("http://localhost:" + port + "/student"
                , HttpMethod.PUT, entity, Student.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getId(), 2L);
        assertEquals(response.getBody().getName(), "Heisenberg");
        assertEquals(response.getBody().getAge(), 52);

    }

    @Test
    void testDeleteStudent() {
        Student student = new Student();
        student.setName("Joseph Williams");
        student.setId(1L);
        student.setAge(14);
        HttpEntity<Student> entity = new HttpEntity<>(student);
        ResponseEntity<Student> response =
                restTemplate.exchange("http://localhost:" + port + "/student/"
                        , HttpMethod.DELETE, entity, Student.class);
        assertNull(response.getBody().getName());
    }

}
