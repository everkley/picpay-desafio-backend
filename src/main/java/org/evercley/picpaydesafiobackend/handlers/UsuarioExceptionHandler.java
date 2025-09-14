package org.evercley.picpaydesafiobackend.handlers;

import org.evercley.picpaydesafiobackend.exceptions.InsufficientFundException;
import org.evercley.picpaydesafiobackend.exceptions.UnauthorizedExcpetion;
import org.evercley.picpaydesafiobackend.exceptions.UsuarioNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UsuarioExceptionHandler {

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<Map<String, Object>> HandlerUserNotFound(UsuarioNotFoundException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND);
        response.put("error", "Usuário não encontrado");
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(InsufficientFundException.class)
    public ResponseEntity<Map<String, Object>> HandlerInsufficientFund(InsufficientFundException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("error", "Fundos insuficientes");
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UnauthorizedExcpetion.class)
    public ResponseEntity<Map<String, Object>> Unauthorized(UnauthorizedExcpetion e) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.UNAUTHORIZED);
        response.put("error", "Transação não autorizada");
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
