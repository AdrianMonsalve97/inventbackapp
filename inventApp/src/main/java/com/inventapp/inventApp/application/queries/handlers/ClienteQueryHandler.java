package com.inventapp.inventApp.application.queries.handlers;


import com.inventapp.inventApp.domain.models.write.Cliente;
import com.inventapp.inventApp.infrastructure.persistence.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteQueryHandler {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteQueryHandler(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerCliente(UUID id) {
        return clienteRepository.findById(id);
    }
}