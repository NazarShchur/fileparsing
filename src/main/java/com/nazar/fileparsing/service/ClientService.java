package com.nazar.fileparsing.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.InsertAllRequest;
import com.google.cloud.bigquery.InsertAllResponse;
import com.nazar.fileparsing.entity.Client;
import com.nazar.fileparsing.repository.ClientOptionalsRepository;
import com.nazar.fileparsing.repository.ClientRepository;
import com.nazar.fileparsing.repository.StorageRepository;
import lombok.AllArgsConstructor;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {
    private final StorageRepository storageRepository;
    private final ClientRepository clientRepository;
    private final ClientOptionalsRepository clientOptionalsRepository;

    public List<Client> parseAvro(String bucketName, String objectName) {
        List<Client> list = new ArrayList<>();
        File file = new File("client.avro");
        storageRepository.getBlob(bucketName, objectName).downloadTo(file.toPath());
        DatumReader<Client> datumReader = new GenericDatumReader<>(Client.SCHEMA$);
        try {
            DataFileReader<Client> dataFileReader = new DataFileReader<>(file, datumReader);
            while (dataFileReader.hasNext()) {
                GenericRecord record = dataFileReader.next();
                list.add(Client.newBuilder()
                        .setId((long) record.get("id"))
                        .setName((String) record.get("name"))
                        .setPhone((String) record.get("phone"))
                        .setAddress((String) record.get("address"))
                        .build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(List<Client> clients) {
        clientRepository.insertAll(clients);
        saveOptionals(clients);
    }

    private void saveOptionals(List<Client> clients){
        List<Client> clientsWithOptionals = clients.stream()
                .filter(client -> client.getAddress() != null || client.getPhone() != null)
                .collect(Collectors.toList());
        clientOptionalsRepository.insertAll(clientsWithOptionals);
    }

}
