package com.inventapp.inventApp.domain.enums;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;

public enum Rol {
    
    ADMIN("Administrador"),
    EXTERNO("Externo");
    private final String nombre;
    Rol(String nombre) {
        this.nombre = nombre;
    }
    @JsonValue
    public String getNombre() {
        return nombre;
    }
    @JsonCreator
    public static Rol fromNombre(String nombre) {
        return Arrays.stream(Rol.values())
                .filter(rol -> rol.nombre.equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rol no valido: " + nombre));
    }
    @Override
    public String toString() {
        return nombre;
    }
}


