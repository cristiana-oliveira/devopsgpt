package com.atu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DevOpsGptController {
    @RequestMapping("/home")
    public ResponseEntity<Object> home() {
        return ResponseEntity.status(HttpStatus.CREATED).body("Welcome home!");
    }
}
