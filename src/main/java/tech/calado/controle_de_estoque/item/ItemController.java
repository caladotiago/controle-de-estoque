package tech.calado.controle_de_estoque.item;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/items")
public class ItemController {

    private final ItemRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Item> findAll() {
        return repository.findAll();
    }
}
