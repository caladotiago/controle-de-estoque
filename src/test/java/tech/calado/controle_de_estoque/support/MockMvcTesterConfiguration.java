package tech.calado.controle_de_estoque.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Configuration
public class MockMvcTesterConfiguration {

	@Bean
	MockMvcTester mockMvcTester(@Autowired WebApplicationContext wac) {
		return MockMvcTester.from(wac)
			.withHttpMessageConverters(List.of(wac.getBean(AbstractJackson2HttpMessageConverter.class)));
	}

}
