package wiremocking;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AppTest {

    @ClassRule
    public static WireMockClassRule wireMockRule = new WireMockClassRule(8083);

    @Rule
    public WireMockClassRule instanceRule = wireMockRule;

    @Test
    public void testGetMockEmployee() {
        stubFor(get(urlEqualTo("/consumer/employee/api/employee/ca05aa3d8cb741dfb6eb3f5877b7d374"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withJsonBody(jsonBody())));

        given()
                .when()
                .get("http://localhost:8083/consumer/employee/api/employee/ca05aa3d8cb741dfb6eb3f5877b7d374")
                .then()
                .body("uid", is("ca05aa3d8cb741dfb6eb3f5877b7d374"))
                .body("name", is("hatusne miku"));
    }

    private JsonNode jsonBody() {
        String bodyString = "{\"uid\":\"ca05aa3d8cb741dfb6eb3f5877b7d374\",\"name\":\"hatusne miku\",\"gender\":\"female\",\"nationality\":\"japan\",\"age\":15,\"salary\":57500}\n";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode body = JsonNodeFactory.instance.objectNode();

        try {
            body = mapper.readTree(bodyString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return body;
    }
}
