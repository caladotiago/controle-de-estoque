package tech.calado.controle_de_estoque.item;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static java.util.UUID.randomUUID;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/items")
public class ItemController {

	private final ItemService itemService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Item> findAll() {
		return itemService.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Item create(@RequestBody @Valid Item item) {
		return itemService.create(item.withId(randomUUID()));
	}

}
