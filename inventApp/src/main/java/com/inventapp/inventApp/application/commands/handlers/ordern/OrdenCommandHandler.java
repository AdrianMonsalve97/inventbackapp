package com.inventapp.inventApp.application.commands.handlers.ordern;

    import com.inventapp.inventApp.application.commands.command.ordern.ActualizarOrdenCommand;
    import com.inventapp.inventApp.application.commands.command.ordern.CrearOrdenCommand;
    import com.inventapp.inventApp.application.commands.command.ordern.EliminarOrdenCommand;
    import com.inventapp.inventApp.domain.models.write.Cliente;
    import com.inventapp.inventApp.domain.models.write.Orden;
    import com.inventapp.inventApp.domain.models.write.Producto;
    import com.inventapp.inventApp.domain.repositories.producto.IProductoRepository;
    import com.inventapp.inventApp.infrastructure.persistence.cliente.ClienteRepository;
    import com.inventapp.inventApp.infrastructure.persistence.orden.OrdenRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    public class OrdenCommandHandler {

        private final OrdenRepository ordenRepository;
        private final ClienteRepository clienteRepository;
        private final IProductoRepository productoRepository;

        @Autowired
        public OrdenCommandHandler(OrdenRepository ordenRepository, ClienteRepository clienteRepository, IProductoRepository productoRepository) {
            this.ordenRepository = ordenRepository;
            this.clienteRepository = clienteRepository;
            this.productoRepository = productoRepository;
        }

        public Orden handle(CrearOrdenCommand command) {
            Orden orden = new Orden();
            Cliente cliente = clienteRepository.findById(command.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente not found"));
            List<Producto> productos = command.getProductoIds().stream()
                .map(productoId -> productoRepository.findById(productoId).orElseThrow(() -> new RuntimeException("Producto not found")))
                .collect(Collectors.toList());
            orden.setCliente(cliente);
            orden.setProductos(productos);
            return ordenRepository.save(orden);
        }

        public Orden handle(ActualizarOrdenCommand command) {
            Orden orden = ordenRepository.findById(command.getId()).orElse(null);
            if (orden != null) {
                List<Producto> productos = command.getProductoIds().stream()
                    .map(productoId -> productoRepository.findById(productoId).orElseThrow(() -> new RuntimeException("Producto not found")))
                    .collect(Collectors.toList());
                Cliente cliente = clienteRepository.findById(command.getClienteId()).orElseThrow(() -> new RuntimeException("Cliente not found"));
                orden.setProductos(productos);
                orden.setCliente(cliente);
                return ordenRepository.save(orden);
            }
            return null;
        }

        public boolean handle(EliminarOrdenCommand command) {
            if (ordenRepository.existsById(command.getId())) {
                ordenRepository.deleteById(command.getId());
                return true;
            }
            return false;
        }
    }