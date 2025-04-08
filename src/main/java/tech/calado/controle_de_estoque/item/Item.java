package tech.calado.controle_de_estoque.item;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import static lombok.AccessLevel.PACKAGE;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = PACKAGE, staticName = "of")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String codigo;

    private String descricao;

    private Grupo grupo;

    private Unidade unidade;

    private double quantidade;

    private double valorUnitario;
}
