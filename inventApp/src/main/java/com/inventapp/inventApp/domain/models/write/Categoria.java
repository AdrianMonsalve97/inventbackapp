package com.inventapp.inventApp.domain.models.write;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(unique = true, nullable = false)
    private String nombre;

    @ManyToMany(mappedBy = "categorias")
    private Set<Producto> productos = new HashSet<>();
}
