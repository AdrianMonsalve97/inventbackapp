package com.inventapp.inventApp.presentation.usuario.controllers.writecontrollers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inventapp.inventApp.domain.dtos.usuario.UsuarioDTO;
import com.inventapp.inventApp.application.commands.command.usuario.CrearUsuarioCommand;
import com.inventapp.inventApp.application.usecases.usuario.CrearUsuarioUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class CrearUsuarioWriteController {

    private final CrearUsuarioUseCase crearUsuarioUseCase;

    public CrearUsuarioWriteController(CrearUsuarioUseCase crearUsuarioUseCase) {
        this.crearUsuarioUseCase = crearUsuarioUseCase;
    }

    @Operation(
        summary = "Crear un nuevo usuario",
        description = "Registra un nuevo usuario en el sistema con su username, email, contraseña y rol."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = UsuarioDTO.class))),
        @ApiResponse(responseCode = "400", description = "Error en la petición",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(responseCode = "409", description = "El usuario ya existe",
            content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/crear")
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody CrearUsuarioCommand command) {
        UsuarioDTO usuario = crearUsuarioUseCase.ejecutar(command);
        return ResponseEntity.ok(usuario);
    }
}

