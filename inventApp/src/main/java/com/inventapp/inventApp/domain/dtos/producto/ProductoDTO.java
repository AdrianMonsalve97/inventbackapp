package com.inventapp.inventApp.domain.dtos.producto;

import com.inventapp.inventApp.domain.models.write.Categoria;
import com.inventapp.inventApp.domain.models.write.Producto;
import com.inventapp.inventApp.domain.models.write.Empresa; // Asegúrate de importar la clase Empresa
import lombok.*;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {

    private UUID id;
    private String codigo;
    private String nombre;
    private String caracteristicas;
    private double precio;
    private String moneda;
    private double precioConvertido;
    private String empresaNit; // Cambiado de UUID a String para representar el NIT
    private Set<String> categorias; // Set de nombres de las categorías
    private boolean activo;

    public ProductoDTO(Producto producto) {
        this.id = producto.getId();
        this.codigo = producto.getCodigo();
        this.nombre = producto.getNombre();
        this.caracteristicas = producto.getCaracteristicas();
        this.precio = producto.getPrecio();
        this.moneda = producto.getMoneda();
        this.precioConvertido = producto.getPrecioConvertido();

        // Si la empresa no es nula, asigna su NIT. Asegúrate de tener el getter `getNit` en la clase Empresa.
        this.empresaNit = (producto.getEmpresa() != null) ? producto.getEmpresa().getNit() : null;

        // Convertir los UUID de categorías a nombres
        this.categorias = producto.getCategorias() != null
                ? producto.getCategorias().stream()
                .map(Categoria::getNombre) // Obtener el nombre de la categoría
                .collect(Collectors.toSet())
                : Collections.emptySet();  // Evitar null en categorías

        this.activo = producto.isActivo();
    }
}
