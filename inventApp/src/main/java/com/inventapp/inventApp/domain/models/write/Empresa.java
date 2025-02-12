    package com.inventapp.inventApp.domain.models.write;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotNull;
    import lombok.*;
    import lombok.experimental.SuperBuilder;
    import org.hibernate.annotations.CreationTimestamp;
    import org.hibernate.annotations.UpdateTimestamp;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.UUID;
    @Entity
    @Table(name = "empresas")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @SuperBuilder
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    public class Empresa {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)  // Se asegura de que el UUID sea generado automáticamente
        private UUID id;  // El tipo UUID para el campo id

        @NotNull
        @Column(nullable = false, unique = true, length = 15)
        private String nit;

        @NotNull
        @Column(nullable = false, unique = true, length = 100)
        private String nombre;

        @NotNull
        @Column(nullable = false, length = 200)
        private String direccion;

        @NotNull
        @Column(nullable = false, length = 20)
        private String telefono;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "admin_id", nullable = false)
        private Usuario admin;

        @OneToMany(mappedBy = "empresa", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
        private List<Producto> productos;

        @CreationTimestamp
        @Column(updatable = false)
        private LocalDateTime fechaCreacion;

        @UpdateTimestamp
        private LocalDateTime fechaActualizacion;

        @Column(nullable = false)
        private boolean activo = true;
    }
