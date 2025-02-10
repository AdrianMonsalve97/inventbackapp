package com.inventapp.inventApp.domain.models.write;

        import jakarta.persistence.*;
        import lombok.*;
        import lombok.experimental.SuperBuilder;

        import java.util.List;
        import java.util.UUID;

        @Entity
        @Getter
        @Setter
        @Table(name = "ordenes")
        @NoArgsConstructor
        @AllArgsConstructor
        @SuperBuilder
        @EqualsAndHashCode(onlyExplicitlyIncluded = true)
        public class Orden {

            @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            private UUID id;

            @ManyToMany
            @JoinTable(
              name = "orden_producto",
              joinColumns = @JoinColumn(name = "orden_id"),
              inverseJoinColumns = @JoinColumn(name = "producto_id"))
            private List<Producto> productos;

            @ManyToOne
            @JoinColumn(name="cliente_id")
            private Cliente cliente;
        }