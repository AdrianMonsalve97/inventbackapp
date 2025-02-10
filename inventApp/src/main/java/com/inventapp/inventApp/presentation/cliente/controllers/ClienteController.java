package com.inventapp.inventApp.presentation.cliente.controllers;



import com.inventapp.inventApp.application.commands.command.cliente.ActualizarClienteCommand;
import com.inventapp.inventApp.application.commands.command.cliente.CrearClienteCommand;
import com.inventapp.inventApp.application.commands.command.cliente.EliminarClienteCommand;
import com.inventapp.inventApp.application.commands.handlers.cliente.ClienteCommandHandler;
import com.inventapp.inventApp.application.queries.handlers.ClienteQueryHandler;
import com.inventapp.inventApp.domain.models.write.Cliente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteCommandHandler clienteCommandHandler;
    private final ClienteQueryHandler clienteQueryHandler;

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody CrearClienteCommand command) {
        Cliente cliente = clienteCommandHandler.handle(command);
        return ResponseEntity.ok(cliente);
    }

    // Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable UUID id, @RequestBody ActualizarClienteCommand command) {
        command = new ActualizarClienteCommand(id, command.getNombre(), command.getDireccion(), command.getTelefono());
        Cliente cliente = clienteCommandHandler.handle(command);
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable UUID id) {
        boolean eliminado = clienteCommandHandler.handle(new EliminarClienteCommand(id));
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        List<Cliente> clientes = clienteQueryHandler.obtenerClientes();
        return ResponseEntity.ok(clientes);
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable UUID id) {
        Optional<Cliente> clienteOptional = clienteQueryHandler.obtenerCliente(id);
        return clienteOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}