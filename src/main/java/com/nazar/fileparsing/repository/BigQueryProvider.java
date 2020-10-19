package com.nazar.fileparsing.repository;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;

public class BigQueryProvider {
    private static final String PROJECT_ID = "fileparcing";
    private static final String PATH_TO_JSON_KEY = "cr.json";
    private static BigQuery bigQuery;

    private BigQueryProvider() {
    }

    public static BigQuery getBigQuery() {
        if (bigQuery == null) {
            //for a local running
//            try {
//                bigQuery = BigQueryOptions
//                        .newBuilder()
//                        .setCredentials(GoogleCredentials
//                                .fromStream(new FileInputStream(PATH_TO_JSON_KEY)))
//                        .setProjectId(PROJECT_ID)
//                        .build()
//                        .getService();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            bigQuery = BigQueryOptions.getDefaultInstance().getService();
        }
        return bigQuery;
    }
}
