    package com.inventapp.inventApp.presentation.orden.controllers;


        import com.inventapp.inventApp.application.commands.command.ordern.ActualizarOrdenCommand;
        import com.inventapp.inventApp.application.commands.command.ordern.CrearOrdenCommand;
        import com.inventapp.inventApp.application.commands.command.ordern.EliminarOrdenCommand;
        import com.inventapp.inventApp.application.commands.handlers.ordern.OrdenCommandHandler;
        import com.inventapp.inventApp.application.queries.handlers.OrdenQueryHandler;
        import com.inventapp.inventApp.domain.models.write.Orden;
        import lombok.RequiredArgsConstructor;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;
        import java.util.Optional;
        import java.util.UUID;

        @RestController
        @RequestMapping("/ordenes")
        @RequiredArgsConstructor
        public class OrdenController {

            private final OrdenCommandHandler ordenCommandHandler;
            private final OrdenQueryHandler ordenQueryHandler;

            @PostMapping
            public ResponseEntity<Orden> crearOrden(@RequestBody CrearOrdenCommand command) {
                Orden orden = ordenCommandHandler.handle(command);
                return ResponseEntity.ok(orden);
            }

            @PutMapping("/{id}")
            public ResponseEntity<Orden> actualizarOrden(@PathVariable UUID id, @RequestBody ActualizarOrdenCommand command) {
                command = new ActualizarOrdenCommand(id, command.getProductoIds(), command.getClienteId());
                Orden orden = ordenCommandHandler.handle(command);
                return orden != null ? ResponseEntity.ok(orden) : ResponseEntity.notFound().build();
            }

            @DeleteMapping("/{id}")
            public ResponseEntity<Void> eliminarOrden(@PathVariable UUID id) {
                boolean eliminado = ordenCommandHandler.handle(new EliminarOrdenCommand(id));
                return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
            }

            @GetMapping("/cliente/{clienteId}")
            public ResponseEntity<List<Orden>> obtenerOrdenesPorCliente(@PathVariable UUID clienteId) {
                List<Orden> ordenes = ordenQueryHandler.obtenerOrdenesPorCliente(clienteId);
                return ResponseEntity.ok(ordenes);
            }

            @GetMapping("/{id}")
            public ResponseEntity<Orden> obtenerOrden(@PathVariable UUID id) {
                Optional<Orden> ordenOptional = ordenQueryHandler.obtenerOrden(id);
                return ordenOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
            }
        }