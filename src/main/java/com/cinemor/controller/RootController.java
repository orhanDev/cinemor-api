package com.cinemor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String home() {
        return "Cinemor API running";
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
