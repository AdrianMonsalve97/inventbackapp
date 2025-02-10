package com.inventapp.inventApp.application.commands.handlers.empresa;

import com.inventapp.inventApp.application.commands.command.empresa.CrearEmpresaCommand;
import com.inventapp.inventApp.application.usecases.empresa.CrearEmpresaUseCase;
import com.inventapp.inventApp.domain.dtos.empresa.EmpresaDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
public class CrearEmpresaHandler {

    private final CrearEmpresaUseCase crearEmpresaUseCase;

    @Autowired
    public CrearEmpresaHandler(CrearEmpresaUseCase crearEmpresaUseCase) {
        this.crearEmpresaUseCase = crearEmpresaUseCase;
    }

    public EmpresaDTO handle(CrearEmpresaCommand command) {
        return crearEmpresaUseCase.ejecutar(command);
    }
}
