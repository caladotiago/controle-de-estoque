package tech.calado.controle_de_estoque.support;

import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Transactional
@Testcontainers
@DirtiesContext
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class PostgreSQLContainerTest {

	@LocalServerPort
	private Integer port;

	@Container
	static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:17-alpine")
		.withInitScript("db/schema.sql");

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.username", container::getUsername);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.jpa.hibernate.ddl-auto", () -> "none");
	}

}
