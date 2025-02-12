package com.inventapp.inventApp.domain.dtos.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String id;
    private String token;
    private String rol;
}