package tech.calado.controle_de_estoque.item;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import tech.calado.controle_de_estoque.support.PostgreSQLContainerTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.from;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static tech.calado.controle_de_estoque.item.Grupo.LIMPEZA;
import static tech.calado.controle_de_estoque.item.Unidade.UN;

class ItemControllerIntegrationTest extends PostgreSQLContainerTest {

	@Autowired
	private MockMvcTester mockMvc;

	@Autowired
	private ItemRepository itemRepository;

	private ObjectMapper objectMapper;

	@BeforeEach
	void setup() {
		itemRepository.deleteAll();
		objectMapper = new ObjectMapper();
	}

	@Test
	void should_retrieve_all_items() throws Exception {
		List<Item> items = List.of(Item.of(randomUUID(), "0001", "Detergente 500ML", LIMPEZA, UN, 1, 2.33));
		itemRepository.saveAll(items);

		assertThat(mockMvc.get().uri("/v1/items")).hasStatusOk()
			.hasContentTypeCompatibleWith(APPLICATION_JSON)
			.bodyJson()
			.convertTo(InstanceOfAssertFactories.list(Item.class))
			.hasSize(1)
			.contains(items.getFirst());
	}

	@Test
	void should_create_item() throws Exception {
		Item itemToCreate = Item.of(null, "0002", "Detergente 500ML", LIMPEZA, UN, 1, 1.33);

		assertThat(itemRepository.findAll()).hasSize(0);

		assertThat(mockMvc.post()
			.uri("/v1/items")
			.contentType(APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(itemToCreate))).hasStatus(HttpStatus.CREATED)
			.hasContentTypeCompatibleWith(APPLICATION_JSON)
			.bodyJson()
			.convertTo(Item.class)
				.returns(itemToCreate.getCodigo(), from(Item::getCodigo));

		assertThat(itemRepository.findAll()).hasSize(1);
	}

	@Test
	void should_create_only_one_item_when_multiple_requests_with_same_code_occurs() {
		Item itemToCreate = Item.of(null, "0002", "Detergente 500ML", LIMPEZA, UN, 1, 1.33);

		assertThat(itemRepository.findAll()).hasSize(0);

		doRequestsInParallel(itemToCreate, 5);

		assertThat(itemRepository.findAll()).hasSize(1);
    }

	private void doRequestsInParallel(Item itemToCreate, int numberOfRequests) {
		try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
			IntStream.range(0, numberOfRequests).forEach(i -> {
				executor.submit(() -> {
					System.out.println("Task " + i + " running on: " + Thread.currentThread().getName());
					try {
						assertThat(mockMvc.post()
								.uri("/v1/items")
								.contentType(APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(itemToCreate)))
								.hasContentTypeCompatibleWith(APPLICATION_JSON);

						System.out.println("Task " + i + " ok: " + Thread.currentThread().getName());
					} catch (JsonProcessingException e) {
						throw new RuntimeException(e);
					}
				});
			});
			executor.shutdown();
		} catch (Exception e) {
			System.out.println(Arrays.toString(e.getStackTrace()));
		}
	}

}
