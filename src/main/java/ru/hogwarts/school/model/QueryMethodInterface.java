package ru.hogwarts.school.model;

import java.util.List;

public interface QueryMethodInterface {
    Integer getAmount();

    Double getAverageAge();

    List<Student> getLastFive();
}
