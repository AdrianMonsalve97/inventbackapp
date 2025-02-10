package com.inventapp.inventApp.domain.models.write;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(unique = true, nullable = false)
    private String codigo;  // Nuevo campo: Código único del producto

    @NotNull
    @Size(min = 2, max = 100)
    @Column(nullable = false)
    private String nombre;

    @Size(max = 255)
    private String caracteristicas;  // Nuevo campo: Descripción del producto

    @NotNull
    @Positive
    @Column(nullable = false)
    private double precio;

    @NotNull
    @Size(min = 3, max = 3)
    @Column(nullable = false)
    private String moneda;  // Nuevo campo: Moneda del precio (ejemplo: USD, COP, EUR)

    @NotNull
    @Positive
    private double precioConvertido;  // Nuevo campo: Precio convertido a una moneda base

    @NotNull
    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToMany
    @JoinTable(
            name = "producto_categoria",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categorias = new HashSet<>();  // Relación con Categoría (muchos a muchos)

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;

    @Column(nullable = false)
    private boolean activo = true;
}
