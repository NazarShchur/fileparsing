package com.nazar.fileparsing.config;

import com.google.cloud.storage.Storage;
import com.nazar.fileparsing.repository.StorageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public Storage storage(){
        return StorageProvider.getStorage();
    }
}
