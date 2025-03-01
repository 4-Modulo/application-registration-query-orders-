package main.api.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity @Table(name = "ItemPedido") @Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String produto;

    private int quantidade;

    private double preco;
}