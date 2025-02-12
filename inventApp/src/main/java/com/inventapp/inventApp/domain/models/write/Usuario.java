package com.inventapp.inventApp.domain.models.write;

import com.inventapp.inventApp.domain.enums.EstadoUsuario;
import com.inventapp.inventApp.domain.enums.Rol;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @NotNull
    @Column (nullable = false, length = 50)
    private String nombre;

    @NotNull
    @Column(nullable = false)
    private String password; 

    @Email
    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Empresa> empresas;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    @Builder.Default
    private EstadoUsuario estadoUsuario = EstadoUsuario.ACTIVO;

    public boolean tienePermiso() {
        throw new UnsupportedOperationException("Método no implementado.");
    }
}
