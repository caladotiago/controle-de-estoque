package tech.calado.controle_de_estoque.common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record Message(String description, @Nullable List<String> details) {

	public static Message of(String description) {
		return new Message(description, null);
	}
}
