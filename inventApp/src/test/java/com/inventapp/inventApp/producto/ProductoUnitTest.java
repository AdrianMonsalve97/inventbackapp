package com.inventapp.inventApp.producto;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.inventapp.inventApp.domain.models.write.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.ResponseEntity;

import com.inventapp.inventApp.application.commands.command.producto.CrearProductoCommand;
import com.inventapp.inventApp.application.commands.command.producto.ActualizarProductoCommand;
import com.inventapp.inventApp.application.usecases.producto.CrearProductoUseCase;
import com.inventapp.inventApp.application.usecases.producto.ActualizarProductoUseCase;
import com.inventapp.inventApp.application.usecases.producto.BorrarProductoUseCase;
import com.inventapp.inventApp.domain.dtos.producto.ProductoDTO;
import com.inventapp.inventApp.domain.repositories.producto.IProductoRepository;
import com.inventapp.inventApp.presentation.producto.controllers.writecontrollers.ProductoController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

class ProductoControllerTest {

    @Mock
    private CrearProductoUseCase crearProductoUseCase;

    @Mock
    private ActualizarProductoUseCase actualizarProductoUseCase;

    @Mock
    private BorrarProductoUseCase borrarProductoUseCase;

    @Mock
    private IProductoRepository productoRepository;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearProducto_ReturnsOk_WhenCommandIsValid() {
        CrearProductoCommand command = new CrearProductoCommand();
        command.setEmpresaId(UUID.randomUUID().toString());

        ProductoDTO productoDTO = new ProductoDTO();
        when(crearProductoUseCase.ejecutar(command)).thenReturn(productoDTO);

        ResponseEntity<ProductoDTO> response = productoController.crearProducto(command);

        assertEquals(ResponseEntity.ok(productoDTO), response);
    }

    @Test
    void crearProducto_ReturnsBadRequest_WhenEmpresaIdIsInvalid() {
        CrearProductoCommand command = new CrearProductoCommand();
        command.setEmpresaId("invalid-uuid");

        try {
            productoController.crearProducto(command);
            fail("Se esperaba una excepción IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("El ID de empresa no es un UUID válido.", e.getMessage());
        }
    }

    @Test
    void actualizarProducto_ReturnsNoContent_WhenCommandIsValid() {
        UUID id = UUID.randomUUID();
        ActualizarProductoCommand command = new ActualizarProductoCommand();
        command.setId(id);

        ResponseEntity<Void> response = productoController.actualizarProducto(id, command);

        verify(actualizarProductoUseCase).ejecutar(command);
        assertEquals(ResponseEntity.noContent().build(), response);
    }

    @Test
    void borrarProducto_ReturnsNoContent_WhenIdIsValid() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = productoController.borrarProducto(id);

        verify(borrarProductoUseCase).ejecutar(id);
        assertEquals(ResponseEntity.noContent().build(), response);
    }


    @Test
    void obtenerProductoPorId_ReturnsOkWithProducto_WhenIdExists() {
        UUID id = UUID.randomUUID();
        ProductoDTO productoDTO = new ProductoDTO();
        when(productoRepository.findById(id)).thenReturn(Optional.<Producto>of(productoDTO));

        ResponseEntity<ProductoDTO> response = productoController.obtenerProductoPorId(id);

        assertEquals(ResponseEntity.ok(productoDTO), response);
    }

    @Test
    void obtenerProductoPorId_ReturnsNotFound_WhenIdDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(productoRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<ProductoDTO> response = productoController.obtenerProductoPorId(id);

        assertEquals(ResponseEntity.notFound().build(), response);
    }
}
