package com.nazar.fileparsing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PostMapping("/")
    public ResponseEntity testPost(){
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("/")
    public String testGet(){
        return "HI";
    }
}
