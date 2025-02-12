package com.inventapp.inventApp.domain.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String mensaje) {
        super(mensaje);
    }

    public InvalidCredentialsException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
