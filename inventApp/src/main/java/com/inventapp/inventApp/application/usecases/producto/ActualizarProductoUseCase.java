package com.inventapp.inventApp.application.usecases.producto;

import com.inventapp.inventApp.application.commands.command.producto.ActualizarProductoCommand;
import com.inventapp.inventApp.domain.models.write.Categoria;
import com.inventapp.inventApp.domain.models.write.Empresa;
import com.inventapp.inventApp.domain.models.write.Producto;
import com.inventapp.inventApp.domain.repositories.categoria.ICategoriaRepository;
import com.inventapp.inventApp.domain.repositories.empresa.IEmpresaRepository;
import com.inventapp.inventApp.domain.repositories.producto.IProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActualizarProductoUseCase {

    private final IProductoRepository productoRepository;
    private final IEmpresaRepository empresaRepository;
    private final ICategoriaRepository categoriaRepository;

    @Transactional
    public void ejecutar(ActualizarProductoCommand command) {
        Producto producto = productoRepository.findById(command.getId())
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        // Validar y asignar empresa
        Empresa empresa = empresaRepository.findById(String.valueOf(command.getEmpresaId()))
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada"));
        producto.setEmpresa(empresa);

        // Validar y asignar categorías
        Set<Categoria> categorias = new HashSet<>(categoriaRepository.findAllById(command.getCategoriasIds()));
        if (categorias.size() != command.getCategoriasIds().size()) {
            throw new IllegalArgumentException("Algunas categorías no existen.");
        }
        producto.setCategorias(categorias);

        // Actualizar demás atributos
        producto.setNombre(command.getNombre());
        producto.setPrecio(command.getPrecio());

        productoRepository.save(producto);
    }
}
