package com.nazar.fileparsing.repository;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileSupplierImpl implements FileSupplier {
    private final String PATH_TO_FILE = "client.avro";

    @Override
    public File supplyFile() {
        return new File(PATH_TO_FILE);
    }
}
