package com.inventapp.inventApp.application.usecases.empresa;

import com.inventapp.inventApp.application.commands.command.empresa.CrearEmpresaCommand;
import com.inventapp.inventApp.domain.dtos.empresa.EmpresaDTO;
import com.inventapp.inventApp.domain.models.write.Empresa;
import com.inventapp.inventApp.domain.models.write.Usuario;
import com.inventapp.inventApp.domain.repositories.empresa.IEmpresaRepository;
import com.inventapp.inventApp.domain.repositories.usuario.IUsuarioRepository;
import com.inventapp.inventApp.infrastructure.mappers.empresa.EmpresaMapper;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CrearEmpresaUseCase {

    private final IEmpresaRepository empresaRepository;
    private final IUsuarioRepository usuarioRepository;
    private final EmpresaMapper empresaMapper;

    @Transactional
    public EmpresaDTO ejecutar(CrearEmpresaCommand command) {
        if (empresaRepository.existsByNit(command.getNit())) {
            throw new IllegalArgumentException("Ya existe una empresa con este NIT");
        }

        Usuario admin = usuarioRepository.findById(UUID.fromString(command.getAdminId().toString()))
                .orElseThrow(() -> new EntityNotFoundException("El usuario administrador no existe"));

        Empresa empresa = empresaMapper.toEntity(command);
        empresa.setAdmin(admin);

        empresa = empresaRepository.save(empresa);

        return empresaMapper.toDTO(empresa);
    }

}