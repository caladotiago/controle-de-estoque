package tech.calado.controle_de_estoque.item;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import tech.calado.controle_de_estoque.common.exception.ConflictException;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

	private final ItemRepository repository;

	public List<Item> findAll() {
		return repository.findAll();
	}

	public Item create(Item itemToCreate) {
		if (repository.existsByCodigo(itemToCreate.getCodigo())) {
			throw new ConflictException("item already exists");
		}

		return repository.save(itemToCreate);
	}

}
