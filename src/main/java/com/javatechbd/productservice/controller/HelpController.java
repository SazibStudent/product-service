package com.javatechbd.productservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("api/v1/help")
@AllArgsConstructor
public class HelpController {

    private Environment environment;

    @GetMapping
    public String port() {
//        throw new RuntimeException("------");
        return environment.getProperty("server.port");
    }
}
