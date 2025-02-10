package com.inventapp.inventApp.domain.exceptions;

public class UsuarioNoAutorizadoException extends RuntimeException {
    public UsuarioNoAutorizadoException(String mensaje) {
        super(mensaje);
    }
}
