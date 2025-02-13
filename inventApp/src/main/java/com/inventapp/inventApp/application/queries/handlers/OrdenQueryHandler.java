package com.inventapp.inventApp.application.queries.handlers;

import com.inventapp.inventApp.domain.models.write.Orden;
import com.inventapp.inventApp.infrastructure.persistence.orden.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrdenQueryHandler {

    private final OrdenRepository ordenRepository;

    @Autowired
    public OrdenQueryHandler(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }

    public List<Orden> obtenerOrdenesPorCliente(UUID clienteId) {
        return ordenRepository.findByClienteId(clienteId);
    }

    public Optional<Orden> obtenerOrden(UUID id) {
        return ordenRepository.findById(id);
    }
    public List<Orden> obtenerOrdenes(){
        return ordenRepository.findAll();
    }
}