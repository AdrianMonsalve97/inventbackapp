package com.inventapp.inventApp.application.queries.handlers.empresa;

import com.inventapp.inventApp.domain.dtos.empresa.EmpresaDTO;
import com.inventapp.inventApp.domain.repositories.empresa.IEmpresaRepository;
import com.inventapp.inventApp.infrastructure.mappers.empresa.EmpresaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListarEmpresaHandler {

    private final IEmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;

    public List<EmpresaDTO> handle() {
        return empresaRepository.findAll()
                .stream()
                .map(empresaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
