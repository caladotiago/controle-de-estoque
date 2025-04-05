package tech.calado.controle_de_estoque.item;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/items")
public class ItemController {

    private final ItemService itemService;

    private final ItemRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Item> findAll() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody Item item) {
        return itemService.create(item);
    }
}
