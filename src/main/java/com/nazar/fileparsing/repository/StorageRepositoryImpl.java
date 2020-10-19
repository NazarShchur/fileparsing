package com.nazar.fileparsing.repository;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
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
