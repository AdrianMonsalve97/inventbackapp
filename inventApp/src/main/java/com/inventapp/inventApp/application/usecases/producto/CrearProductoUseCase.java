package com.inventapp.inventApp.application.usecases.producto;

                        import com.inventapp.inventApp.application.commands.command.producto.CrearProductoCommand;
                        import com.inventapp.inventApp.application.commands.handlers.producto.CurrencyConversionService;
                        import com.inventapp.inventApp.domain.dtos.producto.ProductoDTO;
                        import com.inventapp.inventApp.domain.models.write.Categoria;
                        import com.inventapp.inventApp.domain.models.write.Empresa;
                        import com.inventapp.inventApp.domain.models.write.Producto;
                        import com.inventapp.inventApp.domain.repositories.categoria.ICategoriaRepository;
                        import com.inventapp.inventApp.domain.repositories.empresa.IEmpresaRepository;
                        import com.inventapp.inventApp.domain.repositories.producto.IProductoRepository;
                        import com.inventapp.inventApp.infrastructure.mappers.producto.ProductoMapper;
                        import jakarta.persistence.EntityNotFoundException;
                        import lombok.RequiredArgsConstructor;
                        import org.springframework.stereotype.Service;
                        import org.springframework.transaction.annotation.Transactional;

                        import java.util.Set;
                        import java.util.UUID;
                        import java.util.stream.Collectors;

                        @Service
                        @RequiredArgsConstructor
                        public class CrearProductoUseCase {

                            private final IProductoRepository productoRepository;
                            private final IEmpresaRepository empresaRepository;
                            private final ICategoriaRepository categoriaRepository;
                            private final ProductoMapper productoMapper;
                            private final CurrencyConversionService currencyConversionService;

                            @Transactional
                            public ProductoDTO ejecutar(CrearProductoCommand command) {
                                UUID empresaUuid;
                                try {
                                    empresaUuid = UUID.fromString(command.getEmpresaId()); // Conversión manual
                                } catch (IllegalArgumentException e) {
                                    throw new IllegalArgumentException("El ID de empresa no es un UUID válido.");
                                }

                                Set<UUID> categoriasUuid = command.getCategorias().stream()
                                        .map(UUID::fromString)
                                        .collect(Collectors.toSet());

                                Empresa empresa = empresaRepository.findById(String.valueOf(empresaUuid))
                                        .orElseThrow(() -> new EntityNotFoundException("La empresa no existe."));

                                Set<Categoria> categorias = categoriasUuid.stream()
                                        .map(id -> categoriaRepository.findById(id)
                                                .orElseThrow(() -> new EntityNotFoundException("La categoría con ID " + id + " no existe.")))
                                        .collect(Collectors.toSet());

                                double precioConvertido = currencyConversionService.convertirMoneda(command.getMoneda(), command.getPrecio(), "USD");
                                Producto producto = productoMapper.toEntity(command, empresa, categorias, precioConvertido);
                                productoRepository.save(producto);
                                return productoMapper.toDTO(producto);
                            }

                        }