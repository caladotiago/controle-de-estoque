package tech.calado.controle_de_estoque.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.calado.controle_de_estoque.support.PostgreSQLContainerTest;

import java.util.List;

import static java.util.UUID.randomUUID;
import static tech.calado.controle_de_estoque.item.Grupo.LIMPEZA;
import static tech.calado.controle_de_estoque.item.Unidade.UN;

public class ItemServiceIntegrationTest extends PostgreSQLContainerTest {

    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    void setup() {
        itemRepository.deleteAll();
    }

    @Test
    void should_retrieve_all_items() {
        List<Item> items = List.of(
                Item.of(
                        randomUUID(),
                        "00001",
                        "Detergente Ype 500ML",
                        LIMPEZA,
                        UN,
                        1,
                        2.33
                )
        );
        itemRepository.saveAll(items);
    }

}
