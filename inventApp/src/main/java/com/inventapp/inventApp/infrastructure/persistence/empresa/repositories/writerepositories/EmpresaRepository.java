package com.inventapp.inventApp.infrastructure.persistence.empresa.repositories.writerepositories;

import com.inventapp.inventApp.domain.models.write.Empresa;
import com.inventapp.inventApp.domain.repositories.empresa.IEmpresaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, String>, IEmpresaRepository {

    Optional<Empresa> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    void deleteByNombre(String nombre);


    List<Empresa> findAll();
}
