package com.inventapp.inventApp.application.usecases.categoria;

import com.inventapp.inventApp.domain.repositories.categoria.ICategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EliminarCategoriaUseCase {

    private final ICategoriaRepository categoriaRepository;

    @Transactional
    public void ejecutar(UUID id) {
        if (!categoriaRepository.existsById(id)) {
            throw new EntityNotFoundException("Categoría no encontrada.");
        }
        categoriaRepository.deleteById(id);
    }
}
