package com.nazar.fileparsing.controller;


import com.nazar.fileparsing.entity.User;
import com.nazar.fileparsing.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClientController {
    private final UserRepository repository;

    @GetMapping
    public String test(){
        User user = new User();
        user.setName("Orest");
        repository.save(user);
        return "Hello World";
    }
}
