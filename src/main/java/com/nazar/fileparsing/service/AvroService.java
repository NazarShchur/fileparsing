package com.nazar.fileparsing.service;

import com.nazar.fileparsing.entity.Client;

import java.util.List;

public interface AvroService {
    List<Client> parseAvroClients(String bucketName, String objectName);
}
