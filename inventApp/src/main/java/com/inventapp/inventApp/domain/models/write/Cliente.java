package com.inventapp.inventApp.domain.models.write;

import jakarta.persistence.*;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clientes")
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {
    @Id
    private UUID id;
    private String nombre;
    private String direccion;
    private String telefono;
    @OneToMany(mappedBy = "cliente")
    private List<Orden> ordenes;


}