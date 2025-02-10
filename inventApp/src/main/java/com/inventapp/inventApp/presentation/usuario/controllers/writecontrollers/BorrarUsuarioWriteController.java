package com.inventapp.inventApp.presentation.usuario.controllers.writecontrollers;
import com.inventapp.inventApp.application.commands.command.usuario.BorrarUsuarioCommand;
import com.inventapp.inventApp.application.usecases.usuario.BorrarUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
@RequiredArgsConstructor
public class BorrarUsuarioWriteController {

    private final BorrarUsuarioUseCase borrarUsuarioUseCase;

    @Operation(
            summary = "Eliminar un usuario",
            description = "Elimina un usuario de la base de datos basado en su username."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/borrar/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarUsuario(@PathVariable String username) {
        borrarUsuarioUseCase.ejecutar(new BorrarUsuarioCommand(username));
    }
}
