package com.kashwaa.checkout.api.exceptionhandlers;

import com.kashwaa.checkout.domain.InvalidOrderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class InvalidOrderExceptionController {

    @ExceptionHandler
    public ResponseEntity<Object> handler(InvalidOrderException exception) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("code", 422);
        jsonMap.put("status", "Invalid Order");
        jsonMap.put("message", exception.getMessage());
        return ResponseEntity.status(422).body(jsonMap);
    }
}
