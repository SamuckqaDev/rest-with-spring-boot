package br.com.matsutech.restwithspringbootjava.IntegrationTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import br.com.matsutech.restwithspringbootjava.configs.TestConfigs;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(locations = "classpath:application-test.yml")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = TestConfigs.class)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    @DisplayName("Should display Swagger UI page")
    public void shouldDisplaySwaggerUiPage() {

        var content = given()
                .basePath("/swagger-ui/index.html")
                .port(TestConfigs.SERVER_PORT)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        assertTrue(content.contains("Swagger UI"));
    }

    @Test
    @DisplayName("Should return HTTP 200 OK")
    public void shouldReturnHttp200Ok() {
        given()
                .basePath("/swagger-ui/index.html")
                .port(TestConfigs.SERVER_PORT)
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200);
    }
}
