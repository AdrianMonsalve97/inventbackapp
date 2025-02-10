package com.inventapp.inventApp.presentation.producto.controllers.writecontrollers;

import com.inventapp.inventApp.application.commands.command.producto.ActualizarProductoCommand;
import com.inventapp.inventApp.application.commands.command.producto.CrearProductoCommand;
import com.inventapp.inventApp.application.queries.handlers.producto.ListarProductosPorEmpresaHandler;
import com.inventapp.inventApp.application.queries.queries.producto.ListarProductosPorEmpresaQuery;
import com.inventapp.inventApp.application.usecases.producto.ActualizarProductoUseCase;
import com.inventapp.inventApp.application.usecases.producto.BorrarProductoUseCase;
import com.inventapp.inventApp.application.usecases.producto.CrearProductoUseCase;
import com.inventapp.inventApp.domain.dtos.producto.ProductoDTO;
import com.inventapp.inventApp.domain.repositories.producto.IProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final CrearProductoUseCase crearProductoUseCase;
    private final ActualizarProductoUseCase actualizarProductoUseCase;
    private final BorrarProductoUseCase borrarProductoUseCase;
    private final IProductoRepository productoRepository;
    private final ListarProductosPorEmpresaHandler listarProductosPorEmpresaHandler;

    @PostMapping("/crear")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody CrearProductoCommand command) {
        try {
            command.setEmpresaId(command.getEmpresaId().toString());  // Conversión manual
            ProductoDTO productoCreado = crearProductoUseCase.ejecutar(command);
            return ResponseEntity.ok(productoCreado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarProducto(@PathVariable UUID id, @RequestBody ActualizarProductoCommand command) {
        command.setId(id);
        actualizarProductoUseCase.ejecutar(command);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarProducto(@PathVariable UUID id) {
        borrarProductoUseCase.ejecutar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        List<ProductoDTO> productos = productoRepository.findAll().stream().map(ProductoDTO::new).toList();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable UUID id) {
        return productoRepository.findById(id)
                .map(producto -> ResponseEntity.ok(new ProductoDTO(producto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<ProductoDTO>> listarPorEmpresa(@PathVariable UUID empresaId) {
        ListarProductosPorEmpresaQuery query = new ListarProductosPorEmpresaQuery(empresaId);
        List<ProductoDTO> productos = listarProductosPorEmpresaHandler.ejecutar(query);
        return ResponseEntity.ok(productos);
    }
}