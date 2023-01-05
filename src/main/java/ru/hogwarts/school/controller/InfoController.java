package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.configurations.PortService;

@RestController
@RequestMapping("info")
public class InfoController {

    private final PortService port;

    public InfoController(PortService port) {
        this.port = port;
    }

    @GetMapping
    public String getPort() {
        return port.getPort();
    }
}
