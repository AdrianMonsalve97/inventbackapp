package com.inventapp.inventApp.domain.enums;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;

public enum Rol {
    
    ADMIN("Administrador", "Acceso total para gestionar empresas y productos"),
    EXTERNO("Externo", "Solo puede visualizar empresas");

    private final String nombre;
    private final String descripcion;

    Rol(String nombre, String descripcion) {
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
    public static Rol fromNombre(String nombre) {
        return Arrays.stream(Rol.values())
                .filter(rol -> rol.nombre.equalsIgnoreCase(nombre))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Rol no vÃƒÂ¡lido: " + nombre));
    }

    @Override
    public String toString() {
        return nombre + " - " + descripcion;
    }
}


