package br.com.matsutech.restwithspringbootjava;

import br.com.matsutech.restwithspringbootjava.IntegrationTest.SwaggerIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestWithSpringBootJavaApplicationTests {

	@Test
	void contextLoads() {
		var swaggerTest = new SwaggerIntegrationTest();

		swaggerTest.shouldDisplaySwaggerUiPage();
		swaggerTest.shouldDisplaySwaggerUiPage();
	}

}
