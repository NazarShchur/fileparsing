package com.nazar.fileparsing.repository.implementation;

import com.nazar.fileparsing.repository.FileSupplier;
import org.springframework.stereotype.Component;

import java.io.File;

public class FileSupplierImpl implements FileSupplier {
    private final String PATH_TO_FILE;

    public FileSupplierImpl(String PATH_TO_FILE) {
        this.PATH_TO_FILE = PATH_TO_FILE;
    }
    @Override
    public File supplyFile() {
        return new File(PATH_TO_FILE);
    }
}
