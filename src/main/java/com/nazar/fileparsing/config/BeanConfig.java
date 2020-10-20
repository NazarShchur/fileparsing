package com.nazar.fileparsing.config;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.storage.Storage;
import com.nazar.fileparsing.entity.Client;
import com.nazar.fileparsing.repository.implementation.BigQueryProvider;
import com.nazar.fileparsing.repository.FileSupplier;
import com.nazar.fileparsing.repository.implementation.FileSupplierImpl;
import com.nazar.fileparsing.repository.implementation.StorageProvider;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.io.DatumReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    private final String PATH_TO_AVRO_FILE = "client.avro";
    @Bean
    public Storage storage() {
        return StorageProvider.getStorage();
    }

    @Bean
    public BigQuery bigQuery() {
        return BigQueryProvider.getBigQuery();
    }

    @Bean
    public DatumReader<Client> datumReader(){
        return new GenericDatumReader<>(Client.SCHEMA$);
    }

    @Bean
    public FileSupplier fileSupplier(){
        return new FileSupplierImpl(PATH_TO_AVRO_FILE);
    }
}
