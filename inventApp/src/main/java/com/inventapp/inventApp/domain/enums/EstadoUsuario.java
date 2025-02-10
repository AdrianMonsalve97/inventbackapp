package com.inventapp.inventApp.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;

public enum EstadoUsuario {
    
    ACTIVO("Activo", "El usuario está activo y puede acceder al sistema"),
    INACTIVO("Inactivo", "El usuario está deshabilitado y no puede acceder");

    private final String nombre;
    private final String descripcion;

    EstadoUsuario(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @JsonValue
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @JsonCreator
    public static EstadoUsuario fromNombre(String nombre) {
        return Arrays.stream(EstadoUsuario.values())
                .filter(estado -> estado.nombre.equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Estado no válido: " + nombre));
    }

    @Override
    public String toString() {
        return nombre + " - " + descripcion;
    }
}
