package com.kunal_vats.billing_service.exception;

import com.kunal_vats.billing_service.entity.response.FailureResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<FailureResponse> handleNotFoundException(Exception e) {
        FailureResponse failureResponse = FailureResponse
                .builder()
                .errorCodes(HttpStatus.NOT_FOUND.toString())
                .errorMessage(e.getMessage())
                .errorType("Requested resource not found.")
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(failureResponse);
    }

}
