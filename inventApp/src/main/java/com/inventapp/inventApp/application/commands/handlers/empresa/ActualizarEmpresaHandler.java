package com.inventapp.inventApp.application.commands.handlers.empresa;

import com.inventapp.inventApp.application.commands.command.empresa.ActualizarEmpresaCommand;
import com.inventapp.inventApp.application.usecases.empresa.ActualizarEmpresaUseCase;
import com.inventapp.inventApp.domain.dtos.empresa.EmpresaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActualizarEmpresaHandler {

    private final ActualizarEmpresaUseCase actualizarEmpresaUseCase;

    public EmpresaDTO handle(ActualizarEmpresaCommand command) {
        return actualizarEmpresaUseCase.ejecutar(command);
    }
}
