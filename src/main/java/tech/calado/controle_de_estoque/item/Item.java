package tech.calado.controle_de_estoque.item;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

	@NotNull
	@Size(min = 5)
	private String codigo;

	@NotBlank
	private String descricao;

	@NotNull
	private Grupo grupo;

	@NotNull
	private Unidade unidade;

	@Min(0)
	private double quantidade;

	@Min(0)
	private double valorUnitario;

	Item withId(UUID id) {
		this.id = id;
		return this;
	}

}
