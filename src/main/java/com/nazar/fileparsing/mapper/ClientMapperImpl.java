package com.nazar.fileparsing.mapper;

import com.nazar.fileparsing.entity.Client;
import com.nazar.fileparsing.entity.ClientFields;
import org.apache.avro.generic.GenericRecord;
import org.springframework.stereotype.Component;

@Component
public class ClientMapperImpl implements ClientMapper {
    @Override
    public Client genericRecordToClient(GenericRecord record) {
        return Client.newBuilder()
                .setId((long) record.get(ClientFields.ID))
                .setName((String) record.get(ClientFields.NAME))
                .setPhone((String) record.get(ClientFields.PHONE))
                .setAddress((String) record.get(ClientFields.ADDRESS))
                .build();
    }
}
