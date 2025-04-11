package tech.calado.controle_de_estoque.item;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import tech.calado.controle_de_estoque.support.PostgreSQLContainerTest;

import java.util.List;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static tech.calado.controle_de_estoque.item.Grupo.LIMPEZA;
import static tech.calado.controle_de_estoque.item.Unidade.UN;

class ItemControllerIntegrationTest extends PostgreSQLContainerTest {

	@Autowired
	private MockMvcTester mockMvc;

	@Autowired
	private ItemRepository itemRepository;

	@BeforeEach
	void setup() {
		itemRepository.deleteAll();
	}

	@Test
	void should_retrieve_all_items() throws Exception {
		List<Item> items = List.of(Item.of(randomUUID(), "00001", "Detergente Ype 500ML", LIMPEZA, UN, 1, 2.33));
		itemRepository.saveAll(items);

		assertThat(mockMvc.get().uri("/v1/items")).hasStatusOk()
			.hasContentTypeCompatibleWith(APPLICATION_JSON)
			.bodyJson()
			.convertTo(InstanceOfAssertFactories.list(Item.class))
			.hasSize(1)
			.contains(items.getFirst());
	}

}
