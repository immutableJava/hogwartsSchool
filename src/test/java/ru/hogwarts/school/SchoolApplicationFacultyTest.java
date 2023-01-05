package ru.hogwarts.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(FacultyController.class)
public class SchoolApplicationFacultyTest {

    private final String name = "Slytherin";
    private final String color = "green-white";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private FacultyService facultyService;

    @Test
    void shouldGetFacultyById() throws Exception {
        Long id = 1L;

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyService.getFacultyById(id)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name));

    }

    @Test
    public void shouldCreateFaculty() throws Exception {
        Long id = 1L;
        Faculty newFaculty = new Faculty();
        newFaculty.setName(name);
        newFaculty.setColor(color);

        Faculty createdFaculty = new Faculty();
        createdFaculty.setId(id);
        createdFaculty.setName(name);
        createdFaculty.setColor(color);

        when(facultyService.createFaculty(newFaculty)).thenReturn(createdFaculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(mapper.writeValueAsString(newFaculty))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateFaculty() throws Exception {
        Faculty newFaculty = new Faculty();
        newFaculty.setName(name);
        newFaculty.setColor(color);
        facultyService.createFaculty(newFaculty);
        newFaculty.setName("Gryffindor");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty", "newFaculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(newFaculty)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteFaculty() throws Exception {

        Faculty newFaculty = new Faculty();

        newFaculty.setId(1L);
        newFaculty.setName(name);
        newFaculty.setColor(color);

        facultyService.createFaculty(newFaculty);
        facultyService.deleteFaculty(1L);
        Mockito.verify(facultyService, times(1)).deleteFaculty(1L);
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/1", "newFaculty"))
                .andExpect(status().isOk());

    }

    @Test
    void shouldFilterArrayListByColor() throws Exception {
        String firstFacultyName = "Slytherin";
        String firstFacultyColor = "green-white";

        String secondFacultyName = "Gryffindor";
        String secondFacultyColor = "red-yellow";

        String thirdFacultyName = "test";
        String thirdFacultyColor = "red-yellow";

        Faculty firstFaculty = new Faculty();
        firstFaculty.setName(firstFacultyName);
        firstFaculty.setColor(firstFacultyColor);

        Faculty secondFaculty = new Faculty();
        secondFaculty.setName(secondFacultyName);
        secondFaculty.setColor(secondFacultyColor);

        Faculty thirdFaculty = new Faculty();
        thirdFaculty.setName(thirdFacultyName);
        thirdFaculty.setColor(thirdFacultyColor);

        List<Faculty> faculties = new ArrayList<>(List.of(secondFaculty, thirdFaculty));


        when(facultyService.findByColor(thirdFacultyColor)).thenReturn(faculties);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findByColor", "faculties")
                        .param("color", thirdFacultyColor)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnFacultyByName() throws Exception {
        Faculty newFaculty = new Faculty();
        newFaculty.setName("Gryffindor");
        newFaculty.setColor("red-yellow");


        when(facultyService.findFacultyByName("Gryffindor")).thenReturn(newFaculty);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findByName/Gryffindor")
                        .content(mapper.writeValueAsString(newFaculty))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("red-yellow"));
    }


    @Test
    void shouldReturnFacultyWithLongestName() throws Exception {
        String firstFacultyName = "Slytherin";
        String firstFacultyColor = "green-white";

        String secondFacultyName = "Gryffindor";
        String secondFacultyColor = "red-yellow";

        String thirdFacultyName = "test";
        String thirdFacultyColor = "red-yellow";

        Faculty firstFaculty = new Faculty();
        firstFaculty.setName(firstFacultyName);
        firstFaculty.setColor(firstFacultyColor);

        Faculty secondFaculty = new Faculty();
        secondFaculty.setName(secondFacultyName);
        secondFaculty.setColor(secondFacultyColor);

        Faculty thirdFaculty = new Faculty();
        thirdFaculty.setName(thirdFacultyName);
        thirdFaculty.setColor(thirdFacultyColor);

        facultyService.createFaculty(firstFaculty);
        facultyService.createFaculty(secondFaculty);
        facultyService.createFaculty(thirdFaculty);

        when(facultyService.findTheLongestFacultyName()).thenReturn(secondFacultyName);

        Assertions.assertEquals(facultyService.findTheLongestFacultyName(), secondFacultyName);


    }


}
