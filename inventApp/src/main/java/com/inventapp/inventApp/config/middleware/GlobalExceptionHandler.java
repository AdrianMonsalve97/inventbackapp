package com.inventapp.inventApp.config.middleware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.inventapp.inventApp.domain.exceptions.UsuarioNoAutorizadoException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception ex) {
        LOGGER.error("❌ Error: {}", ex.getMessage());

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("mensaje", "Ocurrió un error inesperado");
        errorResponse.put("error", ex.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(UsuarioNoAutorizadoException.class)
    public ResponseEntity<Map<String, Object>> handleUsuarioNoAutorizado(UsuarioNoAutorizadoException ex) {
        LOGGER.warn("⚠️ Error de negocio: {}", ex.getMessage());

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("mensaje", ex.getMessage());
        errorResponse.put("detalle", "No tienes permisos suficientes para esta acción.");
        errorResponse.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(IllegalArgumentException ex) {
        LOGGER.warn("⚠️ Error de solicitud: {}", ex.getMessage());

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("mensaje", "Solicitud inválida");
        errorResponse.put("detalle", ex.getMessage());
        errorResponse.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
