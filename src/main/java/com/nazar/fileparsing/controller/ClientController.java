package com.nazar.fileparsing.controller;

import com.nazar.fileparsing.entity.Client;
import com.nazar.fileparsing.service.AvroService;
import com.nazar.fileparsing.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final AvroService avroService;

    @PostMapping("/parse")
    public ResponseEntity parseAvroAndWriteToBigQuery(@RequestParam String bucketName, @RequestParam String objectName) throws IOException {
        List<Client> clientList = avroService.parseAvroClients(bucketName, objectName);
        clientService.save(clientList);
        clientService.saveOptionalFields(clientList);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
