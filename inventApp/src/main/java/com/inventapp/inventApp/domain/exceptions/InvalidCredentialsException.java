package com.inventapp.inventApp.domain.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String mensaje) {
        super(mensaje); // Pasa el mensaje a la clase padre
    }

    public InvalidCredentialsException(String mensaje, Throwable causa) {
        super(mensaje, causa); // Pasa el mensaje y la causa (otra excepción)
    }
}
