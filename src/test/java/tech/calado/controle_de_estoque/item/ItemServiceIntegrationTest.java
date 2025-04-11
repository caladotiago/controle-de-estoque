package tech.calado.controle_de_estoque.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import tech.calado.controle_de_estoque.support.PostgreSQLContainerTest;

import java.util.List;

import static java.util.UUID.randomUUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tech.calado.controle_de_estoque.item.Grupo.LIMPEZA;
import static tech.calado.controle_de_estoque.item.Unidade.UN;

@AutoConfigureMockMvc
public class ItemServiceIntegrationTest extends PostgreSQLContainerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void setup() {
        itemRepository.deleteAll();
    }

    @Test
    void should_retrieve_all_items() throws Exception {
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

        this.mockMvc.perform(get("/v1/items"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

}
