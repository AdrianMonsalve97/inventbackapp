package com.inventapp.inventApp.presentation.empresa.controllers.writecontrollers;

import com.inventapp.inventApp.application.commands.command.empresa.CrearEmpresaCommand;
import com.inventapp.inventApp.application.commands.handlers.empresa.CrearEmpresaHandler;
import com.inventapp.inventApp.domain.dtos.empresa.EmpresaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
@Tag(name = "Empresas", description = "Operaciones para gestionar empresas")
public class CrearEmpresaWriteController {
    private final CrearEmpresaHandler crearEmpresaHandler;

    @PostMapping
    @Operation(summary = "Crear una nueva empresa", description = "Este endpoint permite registrar una nueva empresa en el sistema.")
    public ResponseEntity<EmpresaDTO> crearEmpresa(@Valid @RequestBody CrearEmpresaCommand command) {
        EmpresaDTO empresaCreada = crearEmpresaHandler.handle(command);
        return ResponseEntity.ok(empresaCreada);
    }
}

