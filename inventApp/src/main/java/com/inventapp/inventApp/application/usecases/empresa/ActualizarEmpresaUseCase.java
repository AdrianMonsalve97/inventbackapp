package com.inventapp.inventApp.application.usecases.empresa;

import com.inventapp.inventApp.application.commands.command.empresa.ActualizarEmpresaCommand;
import com.inventapp.inventApp.domain.dtos.empresa.EmpresaDTO;
import com.inventapp.inventApp.domain.models.write.Empresa;
import com.inventapp.inventApp.domain.repositories.empresa.IEmpresaRepository;
import com.inventapp.inventApp.infrastructure.mappers.empresa.EmpresaMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ActualizarEmpresaUseCase {

    private final IEmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper; 

    @Transactional
    public EmpresaDTO ejecutar(ActualizarEmpresaCommand command) {
        Empresa empresa = empresaRepository.findById(command.getNit())
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con ID: " + command.getId()));

        empresa.setNombre(command.getNombre());
        empresa.setDireccion(command.getDireccion());
        empresa.setTelefono(command.getTelefono());

        empresaRepository.save(empresa);

        return empresaMapper.toDTO(empresa);
    }
}
