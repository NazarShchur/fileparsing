package com.nazar.fileparsing.controller;

import com.nazar.fileparsing.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/")
    public ResponseEntity parseAvroAndWriteToBigQuery(@RequestParam String bucketName, @RequestParam String objectName){
        clientService.save(clientService.parseAvro(bucketName, objectName));
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("/")
    public String testGet(){
        return "HI";
    }
}
