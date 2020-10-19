package com.nazar.fileparsing.service;

import com.nazar.fileparsing.entity.Client;
import com.nazar.fileparsing.entity.ClientFields;
import com.nazar.fileparsing.mapper.ClientMapper;
import com.nazar.fileparsing.repository.ClientOptionalsRepository;
import com.nazar.fileparsing.repository.ClientRepository;
import com.nazar.fileparsing.repository.FileSupplier;
import com.nazar.fileparsing.repository.StorageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ClientService {
    private final StorageRepository storageRepository;
    private final ClientRepository clientRepository;
    private final ClientOptionalsRepository clientOptionalsRepository;
    private final ClientMapper clientMapper;
    private final FileSupplier fileSupplier;
    private final DatumReader<Client> datumReader;

    public List<Client> parseAvro(String bucketName, String objectName) throws IOException {
        List<Client> list = new ArrayList<>();
        File file = fileSupplier.supplyFile();
        storageRepository.getBlob(bucketName, objectName).downloadTo(file.toPath());
        DataFileReader <Client> dataFileReader = new DataFileReader<>(file, datumReader);
        while (dataFileReader.hasNext()) {
            list.add(clientMapper.genericRecordToClient(dataFileReader.next()));
        }

        return list;
    }

    @Async
    public void save(List<Client> clients) {
        log.info("start saving all");
        clientRepository.insertAll(clients);
        log.info("end saving all");
    }

    @Async
    public void saveOptionals(List<Client> clients) {
        log.info("start saving optionals");
        List<Client> clientsWithOptionals = clients.stream()
                .filter(client -> client.getAddress() != null || client.getPhone() != null)
                .collect(Collectors.toList());
        clientOptionalsRepository.insertAll(clientsWithOptionals);
        log.info("end saving optionals");
    }


}
