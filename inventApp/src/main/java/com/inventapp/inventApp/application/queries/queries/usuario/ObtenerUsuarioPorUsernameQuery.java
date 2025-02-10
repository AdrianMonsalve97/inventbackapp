package com.inventapp.inventApp.application.queries.queries.usuario;

public class ObtenerUsuarioPorUsernameQuery {
    private final String username;

    public ObtenerUsuarioPorUsernameQuery(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
