package com.nazar.fileparsing.service.implementation;

import com.nazar.fileparsing.entity.Client;
import com.nazar.fileparsing.exception.FileProblemException;
import com.nazar.fileparsing.mapper.ClientMapper;
import com.nazar.fileparsing.repository.FileSupplier;
import com.nazar.fileparsing.repository.StorageRepository;
import com.nazar.fileparsing.service.AvroService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class AvroServiceImpl implements AvroService {
    private final StorageRepository storageRepository;
    private final ClientMapper clientMapper;
    private final FileSupplier fileSupplier;
    private final DatumReader<Client> datumReader;

    @Override
    public List<Client> parseAvroClients(String bucketName, String objectName) {
        List<Client> list = new ArrayList<>();
        File file = fileSupplier.supplyFile();
        storageRepository.getBlob(bucketName, objectName).downloadTo(file.toPath());
        DataFileReader<Client> dataFileReader;
        try {
            dataFileReader = new DataFileReader<>(file, datumReader);
        } catch (IOException e) {
            log.info("An error has occured while reading file " + file.getName() + "\n" + e.getMessage());
            throw new FileProblemException("Error while reading file");
        }
        while (dataFileReader.hasNext()) {
            list.add(clientMapper.genericRecordToClient(dataFileReader.next()));
        }
        file.delete();
        return list;
    }
}
