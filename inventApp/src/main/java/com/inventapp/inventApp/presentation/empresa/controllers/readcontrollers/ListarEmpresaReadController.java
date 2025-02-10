package com.inventapp.inventApp.presentation.empresa.controllers.readcontrollers;

import com.inventapp.inventApp.application.queries.handlers.empresa.ListarEmpresaHandler;
import com.inventapp.inventApp.domain.dtos.empresa.EmpresaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
@Tag(name = "Empresas", description = "Operaciones para gestionar empresas")
public class ListarEmpresaReadController {
    private final ListarEmpresaHandler listarEmpresaHandler;

    @GetMapping
    @Operation(summary = "Listar todas las empresas", description = "Devuelve una lista con todas las empresas registradas")
    public ResponseEntity<List<EmpresaDTO>> listarEmpresas() {
        List<EmpresaDTO> empresas = listarEmpresaHandler.handle();
        return ResponseEntity.ok(empresas);
    }
}
