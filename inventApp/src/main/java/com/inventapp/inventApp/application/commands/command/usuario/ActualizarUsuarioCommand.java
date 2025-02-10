package com.inventapp.inventApp.application.commands.command.usuario;

public class ActualizarUsuarioCommand {
    private String userName;
    private String nombre;
    private String email;

    public ActualizarUsuarioCommand(String userName, String nombre, String email) {
        this.userName = userName;
        this.nombre = nombre;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}