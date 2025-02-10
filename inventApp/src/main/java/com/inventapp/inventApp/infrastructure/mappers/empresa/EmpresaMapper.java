package com.inventapp.inventApp.infrastructure.mappers.empresa;

import com.inventapp.inventApp.application.commands.command.empresa.CrearEmpresaCommand;
import com.inventapp.inventApp.domain.dtos.empresa.EmpresaDTO;
import com.inventapp.inventApp.domain.models.write.Empresa;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmpresaMapper {

    // Eliminar la asignación del ID porque es autogenerado
    public Empresa toEntity(CrearEmpresaCommand command) {
        return Empresa.builder()
                .nit(command.getNit())
                .nombre(command.getNombre())
                .direccion(command.getDireccion())
                .telefono(command.getTelefono())
                .activo(true)
                .build();
    }

    // En el método toDTO, no es necesario convertir el id a String, ya que ya es UUID
    public EmpresaDTO toDTO(Empresa empresa) {
        return EmpresaDTO.builder()
                .id(UUID.fromString(empresa.getId().toString()))  // Convierte el UUID a String para el DTO
                .nit(empresa.getNit())
                .nombre(empresa.getNombre())
                .direccion(empresa.getDireccion())
                .telefono(empresa.getTelefono())
                .adminId(UUID.fromString(empresa.getAdmin().getId().toString()))
                .build();
    }
}
