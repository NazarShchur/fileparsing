package com.nazar.fileparsing.mapper;

import com.nazar.fileparsing.entity.Client;
import org.apache.avro.generic.GenericRecord;

public interface ClientMapper {
    Client genericRecordToClient(GenericRecord record);
}
