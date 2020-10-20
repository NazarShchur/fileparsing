package com.nazar.fileparsing.service;

import com.nazar.fileparsing.entity.Client;

import java.util.List;

public interface ClientService {
    void save(List<Client> clientList);
    void saveOptionalFields(List<Client> clientList);
}
