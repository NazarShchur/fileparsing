package com.nazar.fileparsing.repository.implementation;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.nazar.fileparsing.repository.StorageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class StorageRepositoryImpl implements StorageRepository {

    private final Storage storage;

    @Override
    public Blob getBlob(String bucketName, String objectName) {
        return storage.get(bucketName, objectName);
    }
}
