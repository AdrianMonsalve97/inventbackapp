package com.inventapp.inventApp.application.commands.handlers.cliente;

            import com.inventapp.inventApp.application.commands.command.cliente.ActualizarClienteCommand;
            import com.inventapp.inventApp.application.commands.command.cliente.CrearClienteCommand;
            import com.inventapp.inventApp.application.commands.command.cliente.EliminarClienteCommand;
            import com.inventapp.inventApp.domain.models.write.Cliente;
            import com.inventapp.inventApp.infrastructure.persistence.cliente.ClienteRepository;
            import org.springframework.beans.factory.annotation.Autowired;
            import org.springframework.stereotype.Service;

            import java.util.Optional;
            import java.util.UUID;

            @Service
            public class ClienteCommandHandler {

                private final ClienteRepository clienteRepository;

                @Autowired
                public ClienteCommandHandler(ClienteRepository clienteRepository) {
                    this.clienteRepository = clienteRepository;
                }

                public Cliente handle(CrearClienteCommand command) {
                    Cliente cliente = new Cliente(command.getId(), command.getNombre(), command.getDireccion(), command.getTelefono(), null);
                    return clienteRepository.save(cliente);
                }

                public Cliente handle(ActualizarClienteCommand command) {
                    Optional<Cliente> clienteOptional = clienteRepository.findById(command.getId());
                    if (clienteOptional.isPresent()) {
                        Cliente cliente = clienteOptional.get();
                        cliente.setNombre(command.getNombre());
                        cliente.setDireccion(command.getDireccion());
                        cliente.setTelefono(command.getTelefono());
                        return clienteRepository.save(cliente);
                    }
                    return null;
                }

                public boolean handle(EliminarClienteCommand command) {
                    if (clienteRepository.existsById(command.getId())) {
                        clienteRepository.deleteById(command.getId());
                        return true;
                    }
                    return false;
                }
            }