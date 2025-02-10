package com.inventapp.inventApp.domain.repositories.empresa;

import com.inventapp.inventApp.domain.models.write.Empresa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEmpresaRepository {

    Optional<Empresa> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
    Optional<Empresa> findByNit(String nit);

    void deleteByNombre(String nombre);

    Empresa save(Empresa empresa);

    Optional<Empresa> findById(String nit);

    List<Empresa> findAll();

    boolean existsByNit(String nit);
}
