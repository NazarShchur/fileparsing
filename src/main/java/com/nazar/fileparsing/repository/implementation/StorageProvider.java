package com.nazar.fileparsing.repository.implementation;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.FileInputStream;

public class StorageProvider {
    private static final String PROJECT_ID = "fileparsing";
    private static final String PATH_TO_JSON_KEY = "cr.json";
    private static Storage storage;

    private StorageProvider() {
    }

    public static Storage getStorage() {
        if (storage == null) {
            //for a local running
            StorageOptions options = null;
            try {
                options = StorageOptions
                        .newBuilder()
                        .setProjectId(PROJECT_ID)
                        .setCredentials(GoogleCredentials
                                .fromStream(new FileInputStream(PATH_TO_JSON_KEY)))
                        .build();
            } catch (Exception e){
                e.printStackTrace();
            }
            //StorageOptions options = StorageOptions.getDefaultInstance();
            storage = options.getService();
        }
        return storage;
    }
}
