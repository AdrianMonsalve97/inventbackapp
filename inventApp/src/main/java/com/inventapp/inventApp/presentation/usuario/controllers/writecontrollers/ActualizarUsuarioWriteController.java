package com.inventapp.inventApp.presentation.usuario.controllers.writecontrollers;

import com.inventapp.inventApp.application.commands.command.usuario.ActualizarUsuarioCommand;
import com.inventapp.inventApp.application.usecases.usuario.ActualizarUsuarioUseCase;
import com.inventapp.inventApp.domain.dtos.usuario.UsuarioDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
@RequiredArgsConstructor
@Validated
public class ActualizarUsuarioWriteController {

    private final ActualizarUsuarioUseCase actualizarUsuarioUseCase;

    @Operation(
            summary = "Actualizar un usuario",
            description = "Actualiza la información de un usuario existente en el sistema."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "400", description = "Error en la petición",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/actualizar")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDTO actualizarUsuario(@RequestBody @Validated ActualizarUsuarioCommand command) {
        return actualizarUsuarioUseCase.ejecutar(command);
    }
}
