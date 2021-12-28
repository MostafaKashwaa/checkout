package com.kashwaa.checkout.api.exceptionhandlers;

import com.kashwaa.checkout.domain.InvalidOrderException;
import com.kashwaa.checkout.domain.PaymentException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PaymentExceptionController {

    @ExceptionHandler
    public ResponseEntity<Object> handler(PaymentException exception) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("code", 502);
        jsonMap.put("status", "The payment portal does not respond");
        jsonMap.put("message", exception.getMessage());
        return ResponseEntity.status(502).body(jsonMap);
    }
}
