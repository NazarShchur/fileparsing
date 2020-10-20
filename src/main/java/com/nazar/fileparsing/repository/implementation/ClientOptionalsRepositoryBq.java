package com.nazar.fileparsing.repository.implementation;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.InsertAllRequest;
import com.nazar.fileparsing.entity.Client;
import com.nazar.fileparsing.repository.ClientOptionalsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nazar.fileparsing.entity.ClientFields.*;

@Repository
@AllArgsConstructor
public class ClientOptionalsRepositoryBq implements ClientOptionalsRepository {
    private final String datasetName = "clients";
    private final String tableId = "clients_optional";
    private final BigQuery bigQuery;

    @Override
    public void insert(Client client) {
        Map<String, Object> row = new HashMap<>();
        row.put(PHONE, client.getPhone());
        row.put(ADDRESS, client.getAddress());
        row.put(CLIENT_ID, client.getId());
        bigQuery.insertAll(InsertAllRequest.newBuilder(datasetName, tableId)
                .addRow(row)
                .build());
    }

    @Override
    public void insertAll(List<Client> clients) {
        clients.forEach(this::insert);
    }
}
