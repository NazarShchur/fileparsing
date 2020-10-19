package com.nazar.fileparsing.service;

import com.nazar.fileparsing.entity.Client;
import com.nazar.fileparsing.repository.StorageRepository;
import lombok.AllArgsConstructor;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    private final StorageRepository storageRepository;

    public List<Client> parseAvro(String bucketName, String objectName) {
        List<Client> list = new ArrayList<>();
        File file = new File("client.avro");
        storageRepository.getBlob(bucketName, objectName).downloadTo(file.toPath());
        DatumReader<Client> datumReader = new GenericDatumReader<>(Client.SCHEMA$);
        try {
            DataFileReader<Client> dataFileReader = new DataFileReader<>(file, datumReader);
            while (dataFileReader.hasNext()) {
                list.add(dataFileReader.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
