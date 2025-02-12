    package com.inventapp.inventApp.presentation.categoria;
    import com.inventapp.inventApp.application.commands.command.categoria.CrearCategoriaCommand;
    import com.inventapp.inventApp.application.commands.command.categoria.ActualizarCategoriaCommand;
    import com.inventapp.inventApp.application.usecases.categoria.*;
    import com.inventapp.inventApp.domain.dtos.categoria.CategoriaDTO;
    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.UUID;

    @RestController("categoriaControllerAPI")
    @RequestMapping("/api/categorias")
    @RequiredArgsConstructor
    public class CategoriaController {

        private final CrearCategoriaUseCase crearCategoriaUseCase;
        private final ActualizarCategoriaUseCase actualizarCategoriaUseCase;
        private final EliminarCategoriaUseCase eliminarCategoriaUseCase;
        private final ConsultarCategoriasUseCase consultarCategoriasUseCase;


        @PostMapping("/crear")
        public ResponseEntity<CategoriaDTO> crear(@RequestBody @Valid CrearCategoriaCommand command) {
            return ResponseEntity.ok(crearCategoriaUseCase.ejecutar(command));
        }

        @PutMapping
        public ResponseEntity<CategoriaDTO> actualizar(@RequestBody ActualizarCategoriaCommand command) {
            return ResponseEntity.ok(actualizarCategoriaUseCase.ejecutar(command));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
            eliminarCategoriaUseCase.ejecutar(id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/categorias")
        @PreAuthorize("hasAuthority('ADMINISTRADOR')")
        public ResponseEntity<List<CategoriaDTO>> listar() {
            return ResponseEntity.ok(consultarCategoriasUseCase.ejecutar());
        }
    }
