package com.inventapp.inventApp.presentation.empresa.controllers.writecontrollers;

import com.inventapp.inventApp.application.commands.command.empresa.BorrarEmpresaCommand;
import com.inventapp.inventApp.application.commands.handlers.empresa.BorrarEmpresaHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
@Tag(name = "Empresas", description = "Operaciones para gestionar empresas")
public class BorrarEmpresaWriteController {
    private final BorrarEmpresaHandler borrarEmpresaHandler;

    @DeleteMapping("/borrar")
    @Operation(summary = "Borrar empresa", description = "Elimina una empresa por su nombre")
    public ResponseEntity<String> borrarEmpresa(@Valid @RequestBody BorrarEmpresaCommand command) {
        borrarEmpresaHandler.handle(command);
        return ResponseEntity.ok("Empresa eliminada correctamente");
    }
}
