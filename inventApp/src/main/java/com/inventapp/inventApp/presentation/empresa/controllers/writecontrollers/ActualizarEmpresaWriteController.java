package com.inventapp.inventApp.presentation.empresa.controllers.writecontrollers;

import com.inventapp.inventApp.application.commands.command.empresa.ActualizarEmpresaCommand;
import com.inventapp.inventApp.application.commands.handlers.empresa.ActualizarEmpresaHandler;
import com.inventapp.inventApp.domain.dtos.empresa.EmpresaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
@Validated
@Tag(name = "Empresas", description = "Operaciones para gestionar empresas")
public class ActualizarEmpresaWriteController {
        private final ActualizarEmpresaHandler actualizarEmpresaHandler;

        @Operation(summary = "Actualizar una empresa", description = "Actualiza los datos de una empresa existente.")
        @PutMapping("/{nit}")
        public ResponseEntity<EmpresaDTO> actualizarEmpresa(@PathVariable String nit, @Valid @RequestBody ActualizarEmpresaCommand command) {
                command.setNit(nit);
                EmpresaDTO empresaActualizada = actualizarEmpresaHandler.handle(command);
                return ResponseEntity.ok(empresaActualizada);
        }
}
