package com.nazar.fileparsing.repository.implementation;


import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.InsertAllRequest;
import com.nazar.fileparsing.entity.Client;
import com.nazar.fileparsing.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nazar.fileparsing.entity.ClientFields.ID;
import static com.nazar.fileparsing.entity.ClientFields.NAME;

@Repository
@AllArgsConstructor
public class ClientRepositoryBq implements ClientRepository {
    private final String datasetName = "clients";
    private final String tableId = "clients";

    private final BigQuery bigQuery;

    @Override
    public void insert(Client client) {
        Map<String, Object> row = new HashMap<>();
        row.put(ID, client.getId());
        row.put(NAME, client.getName());
        bigQuery.insertAll( InsertAllRequest.newBuilder(datasetName, tableId)
                .addRow(row)
                .build());
    }

    @Override
    public void insertAll(List<Client> clients) {
        clients.forEach(this::insert);
    }
}
