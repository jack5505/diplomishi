package com.tuit.diplomish.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HelloController {
    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Bolla yaxshimi akan qiziq ?? ");
    }
}
