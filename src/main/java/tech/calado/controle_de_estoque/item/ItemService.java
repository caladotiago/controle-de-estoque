package tech.calado.controle_de_estoque.item;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService {

	private final ItemRepository repository;

	public Iterable<Item> findAll() {
		return repository.findAll();
	}

	public Item create(Item itemToCreate) {
		if (repository.existsByCodigo(itemToCreate.getCodigo())) {
			throw new RuntimeException("item already exists");
		}

		return repository.save(itemToCreate);
	}

}
