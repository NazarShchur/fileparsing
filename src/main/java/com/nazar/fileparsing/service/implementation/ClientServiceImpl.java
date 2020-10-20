package com.nazar.fileparsing.service.implementation;

import com.nazar.fileparsing.entity.Client;
import com.nazar.fileparsing.repository.ClientOptionalsRepository;
import com.nazar.fileparsing.repository.ClientRepository;
import com.nazar.fileparsing.service.ClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientOptionalsRepository clientOptionalsRepository;


    @Async
    public void save(List<Client> clients) {
        log.info("start saving all");
        clientRepository.insertAll(clients);
        log.info("end saving all");
    }

    @Async
    public void saveOptionalFields(List<Client> clients) {
        log.info("start saving optionals");
        List<Client> clientsWithOptionals = clients.stream()
                .filter(client -> client.getAddress() != null || client.getPhone() != null)
                .collect(Collectors.toList());
        clientOptionalsRepository.insertAll(clientsWithOptionals);
        log.info("end saving optionals");
    }

}
