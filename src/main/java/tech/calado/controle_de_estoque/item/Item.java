package tech.calado.controle_de_estoque.item;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@ToString
@EqualsAndHashCode
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
