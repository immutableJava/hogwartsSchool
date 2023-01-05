package ru.hogwarts.school.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("second")
public class SecondPortServiceImpl implements PortService {
    public String getPort() {
        return "9090";
    }
}
