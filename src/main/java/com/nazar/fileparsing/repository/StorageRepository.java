package com.nazar.fileparsing.repository;

import com.google.cloud.storage.Blob;

public interface StorageRepository {
    Blob getBlob(String bucketName, String objectName);
}
