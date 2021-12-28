package com.kashwaa.checkout.api.exceptionhandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
@ControllerAdvice
public class RequestNotReadableExceptionController {

    @ExceptionHandler
    public ResponseEntity<Object> handler(HttpMessageNotReadableException exception) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("code", 400);
        jsonMap.put("status", "Bad Request");
        jsonMap.put("message", "Request is not readable.");
        return ResponseEntity.status(400).body(jsonMap);
    }
}
