package tech.calado.controle_de_estoque.item;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PACKAGE, staticName = "of")
public class Item {

    @Id
    private UUID id;

    private String codigo;

    private String descricao;

    private Grupo grupo;

    private Unidade unidade;

    private double quantidade;

    private double valorUnitario;

    Item withId(UUID id) {
        this.id = id;
        return this;
    }

}
