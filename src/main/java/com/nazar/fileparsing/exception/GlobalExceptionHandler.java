package com.nazar.fileparsing.exception;

import org.apache.avro.AvroRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.apache.avro.InvalidAvroMagicException;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({IOException.class, FileProblemException.class})
    public ResponseEntity handleIOException(IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    @ExceptionHandler({InvalidAvroMagicException.class, AvroRuntimeException.class})
    public ResponseEntity handleAvroException(InvalidAvroMagicException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
