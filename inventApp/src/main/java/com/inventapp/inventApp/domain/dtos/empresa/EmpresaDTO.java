package com.inventapp.inventApp.domain.dtos.empresa;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmpresaDTO {

    private UUID id;
    @NotNull
    @Size(min = 8, max = 15)
    private String nit;
    @NotNull
    @Size(min = 2, max = 100)
    private String nombre;
    @NotNull
    @Size(min = 5, max = 200)
    private String direccion;
    @NotNull
    @Size(min = 7, max = 20)
    private String telefono;
    @NotNull
    private UUID adminId;
    public EmpresaDTO(UUID id, String nit, String nombre, String direccion, String telefono, UUID adminId) {
        this.id = id;
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.adminId = adminId;
    }
}
