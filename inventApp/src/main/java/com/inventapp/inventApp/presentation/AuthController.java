package com.inventapp.inventApp.presentation;

import com.inventapp.inventApp.application.commands.handlers.login.LoginHandler;
import com.inventapp.inventApp.domain.dtos.login.LoginCommand;
import com.inventapp.inventApp.domain.dtos.usuario.LoginResponse;
import com.inventapp.inventApp.domain.dtos.usuario.UsuarioLogDTO;
import com.inventapp.inventApp.domain.exceptions.InvalidCredentialsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginHandler loginHandler;

    @Operation(summary = "Inicio de sesión", description = "Permite a un usuario iniciar sesión proporcionando su nombre de usuario y contraseña. Retorna un token JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso, devuelve el token JWT.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud. Verifica las credenciales.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginCommand loginCommand) {
        try {
            LoginResponse loginResponse = loginHandler.handle(loginCommand);
            return ResponseEntity.ok(loginResponse);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}