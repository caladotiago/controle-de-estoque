package tech.calado.controle_de_estoque.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@ToString
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String codigo;

    private String descricao;

    private long quantidade;

    @Column(name = "custoUnitario")
    private double custoUnitario;
}
