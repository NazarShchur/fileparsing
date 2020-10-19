package com.nazar.fileparsing.repository;

import com.nazar.fileparsing.entity.Client;

import java.util.List;

public interface ClientRepository {
    void insert(Client client);
    void insertAll(List<Client> clients);
}
