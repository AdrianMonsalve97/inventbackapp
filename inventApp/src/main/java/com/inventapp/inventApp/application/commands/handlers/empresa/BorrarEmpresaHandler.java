package com.inventapp.inventApp.application.commands.handlers.empresa;

import com.inventapp.inventApp.application.commands.command.empresa.BorrarEmpresaCommand;
import com.inventapp.inventApp.domain.repositories.empresa.IEmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BorrarEmpresaHandler {

    private final IEmpresaRepository empresaRepository;

    @Transactional
    public void handle(BorrarEmpresaCommand command) {
        if (!empresaRepository.existsByNombre(command.getNombre())) {
            throw new EntityNotFoundException("No se encontró una empresa con el nombre: " + command.getNombre());
        }
        empresaRepository.deleteByNombre(command.getNombre());
    }
}
