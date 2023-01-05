package ru.hogwarts.school.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("first")
public class FirstPortServiceImpl implements PortService {
    public String getPort() {
        return "8080";
    }
}
